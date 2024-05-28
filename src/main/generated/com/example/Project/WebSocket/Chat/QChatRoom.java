package com.example.Project.WebSocket.Chat;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChatRoom is a Querydsl query type for ChatRoom
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatRoom extends EntityPathBase<ChatRoom> {

    private static final long serialVersionUID = 828528732L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChatRoom chatRoom = new QChatRoom("chatRoom");

    public final ListPath<com.example.Project.WebSocket.Alarm.Alarm, com.example.Project.WebSocket.Alarm.QAlarm> alarmList = this.<com.example.Project.WebSocket.Alarm.Alarm, com.example.Project.WebSocket.Alarm.QAlarm>createList("alarmList", com.example.Project.WebSocket.Alarm.Alarm.class, com.example.Project.WebSocket.Alarm.QAlarm.class, PathInits.DIRECT2);

    public final ListPath<ChatMessage, QChatMessage> chatMessagesList = this.<ChatMessage, QChatMessage>createList("chatMessagesList", ChatMessage.class, QChatMessage.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.example.Project.User.QSiteUser user1;

    public final com.example.Project.User.QSiteUser user2;

    public QChatRoom(String variable) {
        this(ChatRoom.class, forVariable(variable), INITS);
    }

    public QChatRoom(Path<? extends ChatRoom> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChatRoom(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChatRoom(PathMetadata metadata, PathInits inits) {
        this(ChatRoom.class, metadata, inits);
    }

    public QChatRoom(Class<? extends ChatRoom> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user1 = inits.isInitialized("user1") ? new com.example.Project.User.QSiteUser(forProperty("user1")) : null;
        this.user2 = inits.isInitialized("user2") ? new com.example.Project.User.QSiteUser(forProperty("user2")) : null;
    }

}

