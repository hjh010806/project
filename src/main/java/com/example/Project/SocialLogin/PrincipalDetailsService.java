package com.example.Project.SocialLogin;

import com.example.Project.User.SiteUser;
import com.example.Project.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    //함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("email : " + email);
        SiteUser userEntity = userRepository.findByEmail(email);
        if (userEntity != null) {
            return new PrincipalDetail(userEntity); // SiteUser 타입을 인자로 하는 생성자
        }

        return null;
    }
}
