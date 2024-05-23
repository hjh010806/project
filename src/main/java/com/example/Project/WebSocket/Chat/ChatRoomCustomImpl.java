package com.example.Project.WebSocket.Chat;

import com.example.Project.User.SiteUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ChatRoomCustomImpl implements ChatRoomCustom {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QChatRoom qChatRoom = QChatRoom.chatRoom;

    @Override
    public Optional<ChatRoom> findByUsers(SiteUser user1, SiteUser user2) {
        return Optional.ofNullable(jpaQueryFactory
                .select(qChatRoom)
                .from(qChatRoom)
                .where((qChatRoom.user1.eq(user1).and(qChatRoom.user2.eq(user2)))
                        .or((qChatRoom.user1.eq(user2).and(qChatRoom.user2.eq(user1)))))
                .fetchOne());
    }
}
