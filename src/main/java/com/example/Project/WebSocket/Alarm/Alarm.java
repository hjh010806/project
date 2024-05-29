package com.example.Project.WebSocket.Alarm;

import com.example.Project.User.SiteUser;
import com.example.Project.WebSocket.Chat.ChatRoom;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private Boolean accept;

    @ManyToOne
    private SiteUser senderUser;

    @ManyToOne
    private SiteUser acceptUser;

    @ManyToOne
    @JsonBackReference
    private ChatRoom chatRoom;

    @Builder
    public Alarm(String message, SiteUser senderUser, SiteUser acceptUser, ChatRoom chatRoom) {
        this.message = message;
        this.senderUser = senderUser;
        this.acceptUser = acceptUser;
        this.chatRoom = chatRoom;
        this.accept = false;
    }

}
