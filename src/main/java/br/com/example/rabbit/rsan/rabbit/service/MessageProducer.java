package br.com.example.rabbit.rsan.rabbit.service;

import br.com.example.rabbit.rsan.rabbit.config.RabbitMQConfig;
import br.com.example.rabbit.rsan.rabbit.entity.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

    private static final Logger log = LoggerFactory.getLogger(MessageProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public MessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(ChatMessage chatMessage) {
        log.info("Enviando mensagem: {}", chatMessage);
        rabbitTemplate.convertAndSend(RabbitMQConfig.CHAT_QUEUE, chatMessage);
        log.info("Mensagem enviada com sucesso!");
    }

    public void sendMessage(String username, String message) {
        ChatMessage chatMessage = new ChatMessage(username, message);
        sendMessage(chatMessage);
    }
}