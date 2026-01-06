package br.com.example.rabbit.rsan.rabbit.controller;

import br.com.example.rabbit.rsan.rabbit.entity.ChatMessage;
import br.com.example.rabbit.rsan.rabbit.service.MessageProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final MessageProducer messageProducer;

    public ChatController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody ChatMessage chatMessage) {
        messageProducer.sendMessage(chatMessage);
        return ResponseEntity.ok("Mensagem enviada com sucesso!");
    }

    @PostMapping("/send/fast")
    public ResponseEntity<String> sendMessageSimple(
            @RequestParam String username,
            @RequestParam String message) {
        messageProducer.sendMessage(username, message);
        return ResponseEntity.ok("Mensagem enviada: " + username + " - " + message);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        messageProducer.sendMessage("Sistema", "Mensagem de teste!");
        return ResponseEntity.ok("Mensagem de teste enviada!");
    }
}