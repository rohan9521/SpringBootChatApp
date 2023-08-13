package com.example.MessengerClone.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ChatMessage {
    private String content;
    private String sender;
    private MessageType messageType;

}
