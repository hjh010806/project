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

    public static final QListMain listMain = new QListMain("listMain");

    public final ListPath<com.example.Project.Answer.Answer, com.example.Project.Answer.QAnswer> answerList = this.<com.example.Project.Answer.Answer, com.example.Project.Answer.QAnswer>createList("answerList", com.example.Project.Answer.Answer.class, com.example.Project.Answer.QAnswer.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QListMain(String variable) {
        super(ListMain.class, forVariable(variable));
    }

    public QListMain(Path<? extends ListMain> path) {
        super(path.getType(), path.getMetadata());
    }

    public QListMain(PathMetadata metadata) {
        super(ListMain.class, metadata);
    }

}

