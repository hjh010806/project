package com.example.Project.SocialLogin;

import lombok.Getter;

import java.util.Map;

@Getter
public class GoogleUserInfo implements OAuth2UserInfo{
    private String id;
    private String name;
    private String email;
    private String imageUrl;
    private String nickname;
    private String provider;
    private String providerId;

    public GoogleUserInfo(Map<String, Object> attributes) {
        this.id = (String) attributes.get("sub");
        this.name = (String) attributes.get("name");
        this.nickname = (String) attributes.get("nickName");
        this.email = (String) attributes.get("email");
        this.imageUrl = (String) attributes.get("picture");
        this.provider = "google"; // 소셜 로그인 공급자를 구글로 설정
        this.providerId = (String) attributes.get("sub"); // 구글 ID를 providerId로 설정
    }


}
