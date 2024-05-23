package com.example.Project.WebSocket.Chat;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    public enum MessageType {
        ENTER, TALK
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sender;

    private String message;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createDate;

    @ManyToOne
    @JsonManagedReference
    private ChatRoom chatRoom;

    public ChatMessage(String message) {
        this.message = message;
    }

    @Builder
    public ChatMessage(String sender,String message,ChatRoom chatRoom,LocalDateTime createDate){
        this.sender = sender;
        this.message = message;
        this.chatRoom = chatRoom;
        this.createDate = createDate;
    }

}
