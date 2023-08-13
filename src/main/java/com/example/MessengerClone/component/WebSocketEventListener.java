package com.example.MessengerClone.component;

import com.example.MessengerClone.model.ChatMessage;
import com.example.MessengerClone.model.MessageType;
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
    private final SimpMessageSendingOperations simpMessageSendingOperations;
    @EventListener
    public void handleWebSocketDisconnectListener(
            SessionDisconnectEvent event
    ){
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String userName = (String) headerAccessor.getSessionAttributes().get("userName");
        if(userName!=null){
            log.info("User Disconnected :{} ",userName);
            var chatMessage = ChatMessage
                    .builder()
                    .messageType(MessageType.LEAVE)
                    .sender(userName)
                    .build();
            simpMessageSendingOperations.convertAndSend(chatMessage);

        }
    }

}
