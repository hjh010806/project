package com.example.Project.WebSocket.Chat;

import com.example.Project.User.SiteUser;
import com.example.Project.WebSocket.Alarm.Alarm;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    List<ChatMessage> chatMessagesList = new ArrayList<>();

    @ManyToOne
    private SiteUser user1;

    @ManyToOne
    private SiteUser user2;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    List<Alarm> alarmList = new ArrayList<>();

    @Builder
    private ChatRoom(SiteUser user1, SiteUser user2) {
        this.user1 = user1;
        this.user2 = user2;
    }
}
