package com.example.Project.WebSocket;

import com.example.Project.WebSocket.Chat.ChatMessageDto;
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

    @MessageMapping("/talk/{id}")
    @SendTo("/sub/talk/{id}")
    public ChatMessageDto message(ChatMessageDto message, @DestinationVariable("id") Long id) throws Exception {
        chatRoomService.saveMessage(message, id);
        return message;
    }

}
