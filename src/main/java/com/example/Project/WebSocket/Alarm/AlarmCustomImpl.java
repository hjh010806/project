package com.example.Project.WebSocket.Alarm;

import com.example.Project.User.SiteUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AlarmCustomImpl implements AlarmCustom {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QAlarm qAlarm = QAlarm.alarm;

    @Override
    public List<Alarm> findByAcceptUser(SiteUser acceptUser) {
        return jpaQueryFactory.select(qAlarm).from(qAlarm).where(qAlarm.acceptUser.eq(acceptUser).and(qAlarm.accept.eq(false))).fetch();
    }
}
