package com.example.Project.WebSocket.Chat;

import com.example.Project.User.SiteUser;
import com.example.Project.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;

    public void saveMessage(ChatMessageDto messageDto, Long id) {
        LocalDateTime createDate = messageDto.getCreateDate();
        ChatRoom chatRoom = chatRoomRepository.findById(id);
        ChatMessage chatMessage = ChatMessage.builder()
                .sender(messageDto.getSender())
                .message(messageDto.getMessage())
                .chatRoom(chatRoom)
                .createDate(createDate)
                .build();
        chatMessageRepository.save(chatMessage);
    }

    public Optional<ChatRoom> getChatRoom(SiteUser user1, SiteUser user2) {
        return this.chatRoomRepository.findByUsers(user1, user2);
    }

    public ChatRoom save(ChatRoom chatRoom) {
        return this.chatRoomRepository.save(chatRoom);
    }

    public ChatRoom findById(Long id) {
        return chatRoomRepository.findById(id);
    }
}
