package com.example.Project.WebSocket.Chat;

import com.example.Project.User.SiteUser;

import java.util.Optional;

public interface ChatRoomCustom {
    Optional<ChatRoom> findByUsers(SiteUser user1, SiteUser user2);
}
