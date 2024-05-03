package com.example.Project.List;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QListMain is a Querydsl query type for ListMain
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QListMain extends EntityPathBase<ListMain> {

    private static final long serialVersionUID = -1969678495L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QListMain listMain = new QListMain("listMain");

    public final ListPath<com.example.Project.Answer.Answer, com.example.Project.Answer.QAnswer> answerList = this.<com.example.Project.Answer.Answer, com.example.Project.Answer.QAnswer>createList("answerList", com.example.Project.Answer.Answer.class, com.example.Project.Answer.QAnswer.class, PathInits.DIRECT2);

    public final com.example.Project.User.QSiteUser author;

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QListMain(String variable) {
        this(ListMain.class, forVariable(variable), INITS);
    }

    public QListMain(Path<? extends ListMain> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QListMain(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QListMain(PathMetadata metadata, PathInits inits) {
        this(ListMain.class, metadata, inits);
    }

    public QListMain(Class<? extends ListMain> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.author = inits.isInitialized("author") ? new com.example.Project.User.QSiteUser(forProperty("author")) : null;
    }

}

