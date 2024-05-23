package com.example.Project.WebSocket.Chat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String>, ChatRoomCustom {
    public ChatRoom findById(Long id);

    public List<ChatRoom> findAllById(Long id);
}

