package Integration.IntegrateOpenAI.service;

import Integration.IntegrateOpenAI.dtos.ChatDTO;
import Integration.IntegrateOpenAI.dtos.ChatGptRequest;
import Integration.IntegrateOpenAI.dtos.ChatGptResponse;
import Integration.IntegrateOpenAI.entity.Chat;
import Integration.IntegrateOpenAI.repository.ChatGptRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatManager implements ChatService{

    @Qualifier("openaiRestTemplate")
    private final RestTemplate restTemplate;

    private final ChatGptRepository chatGptRepository;

    @Value("${chatgpt.model}")
    private String model;

    @Value("${chatgpt.url}")
    private String url;

    @Value("${chatgpt.apikey}")
    private String APIKEY;

    public ChatManager(RestTemplate restTemplate, ChatGptRepository chatGptRepository) {
        this.restTemplate = restTemplate;
        this.chatGptRepository = chatGptRepository;
    }

    @Override
    public String getChat(ChatDTO chat) {
        ChatGptRequest request = new ChatGptRequest(model, chat.getMessage());
        ChatGptResponse chatGptResponse = restTemplate.postForObject(url, request, ChatGptResponse.class);
        return chatGptResponse.getChoices().get(0).getMessage().getContent();
    }


    @Override
    public String add(ChatDTO chat) {
        Chat chatForDb = new Chat();
        chatForDb.setMessage(getChat(chat));
        return chatGptRepository.save(chatForDb).getMessage();
    }

    @Override
    public Chat getChats(Long id) {
        return chatGptRepository.findById(id).orElse(null);
    }
}
