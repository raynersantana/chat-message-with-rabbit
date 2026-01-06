package br.com.example.rabbit.rsan.rabbit.service;

import br.com.example.rabbit.rsan.rabbit.config.RabbitMQConfig;
import br.com.example.rabbit.rsan.rabbit.entity.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageReceiver {

    private static final Logger log = LoggerFactory.getLogger(MessageReceiver.class);

    /**
     * Recebe e processa mensagens da fila do chat
     * O @RabbitListener faz com que este método escute automaticamente a fila
     */
    @RabbitListener(queues = RabbitMQConfig.CHAT_QUEUE)
    public void receiveMessage(ChatMessage chatMessage) {
        log.info("========================================");
        log.info(" Nova mensagem recebida:");
        log.info("   Usuário: {}", chatMessage.getUsername());
        log.info("   Mensagem: {}", chatMessage.getMessage());
        log.info("   Horário: {}", chatMessage.getTimestamp());
        log.info("========================================");
    }
}