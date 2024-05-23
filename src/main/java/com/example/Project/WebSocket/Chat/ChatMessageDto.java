package com.example.Project.WebSocket.Chat;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatMessageDto {
    private String message;

    private String sender;

    private LocalDateTime createDate;
}
