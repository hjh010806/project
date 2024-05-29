package com.example.Project.WebSocket.Alarm;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlarmDto {
    private String message;

    private Boolean accept;

    private String sendUserNickname;

    private String acceptUserNickname;

    private Long chatRoomId;
}
