package com.example.Project.SocialLogin;

public interface OAuth2UserInfo {
    String getId();
    String getName();
    String getNickname();
    String getEmail();
    String getImageUrl();
    String getProvider(); // 소셜 로그인 공급자(provider)를 가져오는 메서드 추가
    String getProviderId();
}
