package com.example.Project.WebSocket.Alarm;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlarmRepository extends JpaRepository<Alarm, Long>, AlarmCustom {
    List<Alarm> findByChatRoomId(Long id);
}
