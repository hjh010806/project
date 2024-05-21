package com.example.Project.User;

import com.example.Project.Likes.Likes;
import com.example.Project.List.Image.Image;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
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

    private String provider;
    private String providerId;

    @OneToMany(mappedBy = "siteUser", cascade = CascadeType.ALL)
    private Set<Likes> likes;

    @OneToMany(mappedBy = "siteUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    private String imageUrl;
    @Builder
    public SiteUser(String name, String password,String nickName ,String email, String role, String provider, String providerId, String number) {
        this.name = name;
        this.password = password;
        this.nickName = nickName;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
        this.number=number;
    }

}
