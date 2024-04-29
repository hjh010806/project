package com.example.Project.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SiteUser {
    // 회원 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 회원 이름
    private String name;
    //회원 비밀번호
    private String password;
    //휴대폰 번호
    @Column(unique = true)
    private String number;
    // 회원 닉네임
    @Column(unique = true)
    private String nickName;
    // 회원 이메일
    @Column(unique = true)
    private String email;
    // 유저 권한
    private String role;

}
