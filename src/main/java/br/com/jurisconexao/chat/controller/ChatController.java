package br.com.jurisconexao.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import br.com.jurisconexao.chat.models.ChatMessage;
import br.com.jurisconexao.chat.models.Message;

@Controller
public class ChatController {
	
	    @Autowired
	    private SimpMessagingTemplate simpMessagingTemplate;

	    @MessageMapping("/message")
	    @SendTo("/chatroom/public")
	    public Message receiveMessage(@Payload Message message){
	        return message;
	    }

	    @MessageMapping("/private-message")
	    public Message recMessage(@Payload Message message){
	        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(),"/private",message);
	        System.out.println(message.toString());
	        return message;
	    }

   

    @MessageMapping("/sendMessage")
    @SendTo("/topic/group")
    public Message broadcastGroupMessage(@Payload Message message) {
        return message;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(
            @Payload ChatMessage chatMessage,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
    

   
}
