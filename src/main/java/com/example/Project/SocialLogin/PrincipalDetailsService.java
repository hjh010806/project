package com.example.Project.SocialLogin;

import com.example.Project.User.SiteUser;
import com.example.Project.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrincipalDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    public void setUserRepository(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    //함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<SiteUser> siteUser = userRepository.findByEmail(email);
        if (!siteUser.isPresent())
            throw new UsernameNotFoundException("해당하는 이메일이 없습니다. : " + email);
        SiteUser user = siteUser.get();
        return new PrincipalDetail(user);
    }
}
