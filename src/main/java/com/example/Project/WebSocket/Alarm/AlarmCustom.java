package com.example.Project.WebSocket.Alarm;

import com.example.Project.User.SiteUser;

import java.util.List;

public interface AlarmCustom {
    List<Alarm> findByAcceptUser(SiteUser acceptUser);
}
