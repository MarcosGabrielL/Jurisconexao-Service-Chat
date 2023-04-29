package br.com.jurisconexao.chat.config;

import br.com.jurisconexao.chat.chat.ChatMessage;
import br.com.jurisconexao.chat.chat.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketEventListener {

    private  SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if (username != null) {
          //  log.info("user disconnected: {}", username);
            var chatMessage = new ChatMessage(
                     MessageType.LEAVE,
                     username);
            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }

}
