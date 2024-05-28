package com.example.Project.WebSocket.Alarm;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAlarm is a Querydsl query type for Alarm
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAlarm extends EntityPathBase<Alarm> {

    private static final long serialVersionUID = 538627879L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAlarm alarm = new QAlarm("alarm");

    public final BooleanPath accept = createBoolean("accept");

    public final com.example.Project.User.QSiteUser acceptUser;

    public final com.example.Project.WebSocket.Chat.QChatRoom chatRoom;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath message = createString("message");

    public final com.example.Project.User.QSiteUser senderUser;

    public QAlarm(String variable) {
        this(Alarm.class, forVariable(variable), INITS);
    }

    public QAlarm(Path<? extends Alarm> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAlarm(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAlarm(PathMetadata metadata, PathInits inits) {
        this(Alarm.class, metadata, inits);
    }

    public QAlarm(Class<? extends Alarm> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.acceptUser = inits.isInitialized("acceptUser") ? new com.example.Project.User.QSiteUser(forProperty("acceptUser")) : null;
        this.chatRoom = inits.isInitialized("chatRoom") ? new com.example.Project.WebSocket.Chat.QChatRoom(forProperty("chatRoom"), inits.get("chatRoom")) : null;
        this.senderUser = inits.isInitialized("senderUser") ? new com.example.Project.User.QSiteUser(forProperty("senderUser")) : null;
    }

}

