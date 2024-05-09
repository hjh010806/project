package com.example.Project.Likes;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
@RequiredArgsConstructor
public class LikesCustomImpl implements LikesCustom {
    private final JPAQueryFactory jpaQueryFactory;
    QLikes qLikes = QLikes.likes;
    @Override
    public Optional<Likes> findByListMainIdAndSiteUserId(Long listMainId, Long UserId) {
         return Optional.ofNullable(jpaQueryFactory.select(qLikes).from(qLikes).where(qLikes.listMain.id.eq(listMainId).and(qLikes.siteUser.id.eq(UserId))).fetchOne());
    }
}
