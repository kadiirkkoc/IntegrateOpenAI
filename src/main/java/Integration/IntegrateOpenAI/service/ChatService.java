package Integration.IntegrateOpenAI.service;

import Integration.IntegrateOpenAI.dtos.ChatDTO;
import Integration.IntegrateOpenAI.entity.Chat;

public interface ChatService {
    String getChat(ChatDTO chat);
    String add(ChatDTO chat);
    Chat getChats(Long id);
}
