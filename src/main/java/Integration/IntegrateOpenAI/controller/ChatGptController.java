
package Integration.IntegrateOpenAI.controller;


import Integration.IntegrateOpenAI.dtos.ChatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Integration.IntegrateOpenAI.repository.ChatGptRepository;
import Integration.IntegrateOpenAI.service.ChatService;

@RestController
@RequestMapping("/openapi")
public class ChatGptController {

    private ChatGptRepository chatGptRepository;
    private ChatService chatService;

    @Autowired
    public ChatGptController(ChatService chatService,ChatGptRepository chatGptRepository) {
        this.chatService = chatService;
        this.chatGptRepository = chatGptRepository;
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody ChatDTO chat){
        return ResponseEntity.ok(chatService.add(chat));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getChat(@PathVariable Long id){
        return new ResponseEntity<>(chatService.getChats(id), HttpStatus.OK);
    }
}