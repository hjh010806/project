package com.example.Project.WebSocket;

import com.example.Project.User.SiteUser;
import com.example.Project.User.UserService;
import com.example.Project.WebSocket.Alarm.Alarm;
import com.example.Project.WebSocket.Alarm.AlarmDto;
import com.example.Project.WebSocket.Alarm.AlarmService;
import com.example.Project.WebSocket.Chat.ChatMessageDto;
import com.example.Project.WebSocket.Chat.ChatRoom;
import com.example.Project.WebSocket.Chat.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class WebSocketController {
    private final ChatRoomService chatRoomService;
    private final UserService userService;
    private final AlarmService alarmService;

    @MessageMapping("/talk/{id}")
    @SendTo("/sub/talk/{id}")
    public ChatMessageDto message(ChatMessageDto message, @DestinationVariable("id") Long id) throws Exception {
        alarmService.saveMessage(message, id);
        return message;
    }

    @MessageMapping("/alarm/{userName}")
    @SendTo("/sub/alarm/{userName}")
    public AlarmDto alarm(AlarmDto alarm) throws Exception {
        SiteUser sendUser = userService.getNickname(alarm.getSendUserNickname());
        SiteUser acceptUser = userService.getNickname(alarm.getAcceptUserNickname());
        ChatRoom chatRoom = chatRoomService.getChatRoomId(alarm.getChatRoomId());
        Alarm alarm1 = Alarm.builder()
                .message(alarm.getMessage())
                .senderUser(sendUser)
                .acceptUser(acceptUser)
                .chatRoom(chatRoom)
                .build();
        alarmService.saveAlarm(alarm1);

        return alarm;
    }

}
