package com.example.Project.User;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSiteUser is a Querydsl query type for SiteUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSiteUser extends EntityPathBase<SiteUser> {

    private static final long serialVersionUID = 85833487L;

    public static final QSiteUser siteUser = new QSiteUser("siteUser");

    public final ListPath<com.example.Project.WebSocket.Chat.ChatRoom, com.example.Project.WebSocket.Chat.QChatRoom> chatRoomList = this.<com.example.Project.WebSocket.Chat.ChatRoom, com.example.Project.WebSocket.Chat.QChatRoom>createList("chatRoomList", com.example.Project.WebSocket.Chat.ChatRoom.class, com.example.Project.WebSocket.Chat.QChatRoom.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.example.Project.List.Image.Image, com.example.Project.List.Image.QImage> images = this.<com.example.Project.List.Image.Image, com.example.Project.List.Image.QImage>createList("images", com.example.Project.List.Image.Image.class, com.example.Project.List.Image.QImage.class, PathInits.DIRECT2);

    public final StringPath imageUrl = createString("imageUrl");

    public final SetPath<com.example.Project.Likes.Likes, com.example.Project.Likes.QLikes> likes = this.<com.example.Project.Likes.Likes, com.example.Project.Likes.QLikes>createSet("likes", com.example.Project.Likes.Likes.class, com.example.Project.Likes.QLikes.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final StringPath nickName = createString("nickName");

    public final StringPath number = createString("number");

    public final StringPath password = createString("password");

    public final StringPath profileUrl = createString("profileUrl");

    public final StringPath provider = createString("provider");

    public final StringPath providerId = createString("providerId");

    public final StringPath role = createString("role");

    public QSiteUser(String variable) {
        super(SiteUser.class, forVariable(variable));
    }

    public QSiteUser(Path<? extends SiteUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSiteUser(PathMetadata metadata) {
        super(SiteUser.class, metadata);
    }

}

