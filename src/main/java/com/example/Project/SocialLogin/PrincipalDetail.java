package com.example.Project.SocialLogin;

import com.example.Project.User.SiteUser;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class PrincipalDetail implements UserDetails, OAuth2User{
    @Setter
    private SiteUser siteUser;
    private Map<String,Object> attributes;

    // 일반 로그인
    public PrincipalDetail(SiteUser siteUser) {
        this.siteUser = siteUser;
    }

    // OAuth2User 로그인
    public PrincipalDetail(SiteUser siteUser,  Map<String, Object> attributes) {
        this.siteUser = siteUser;
        this.attributes = attributes;
    }
    //OAuth2User의 메서드
    @Override
    public Map<String, Object> getAttributes(){
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return siteUser.getRole();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return siteUser.getPassword();
    }

    @Override
    public String getUsername() {
        return siteUser.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return siteUser.getEmail();
    }
    public String getEmail(){
        return siteUser.getEmail();
    }
    public String getNickname(){
        return siteUser.getNickName();
    }
    public SiteUser getUser(){
        return siteUser;
    }

}
