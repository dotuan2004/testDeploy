package com.example.demo.presentation.controller;
import com.example.demo.persistence.entity.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import java.util.Date;
@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {

        chatMessage.setTimestamp(new Date());
        // Gửi tin nhắn đến các client đang lắng nghe /topic/messages
        messagingTemplate.convertAndSend("/topic/messages", chatMessage);
        System.out.println("Received message: " + chatMessage);
        return chatMessage;
    }
}
