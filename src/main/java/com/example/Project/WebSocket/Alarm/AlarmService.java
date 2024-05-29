package com.example.Project.WebSocket.Alarm;

import com.example.Project.User.SiteUser;
import com.example.Project.WebSocket.Chat.ChatMessageDto;
import com.example.Project.WebSocket.Chat.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlarmService {
    private final AlarmRepository alarmRepository;
    private final ChatRoomService chatRoomService;

    public List<Alarm> findByChatRoomId(Long id) {
        return alarmRepository.findByChatRoomId(id);
    }

    public void saveMessage(ChatMessageDto message, Long id) {
        chatRoomService.saveMessage(message, id);
    }

    public void saveAlarm(Alarm alarm1) {
        alarmRepository.save(alarm1);
    }

    public void delete(Alarm alarm) {
        alarmRepository.delete(alarm);
    }

    public List<Alarm> findByAcceptUser(SiteUser user) {
        return this.alarmRepository.findByAcceptUser(user);
    }
}
