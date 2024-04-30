package com.example.Project.User;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean hasUser(String number, String nickName, String email) {
        return userRepository.checklist(number, nickName, email).size() > 0;
    }

    public SiteUser create(String name, String email, String password, String nickName, String number) {
        SiteUser user = SiteUser.builder()
                .name(name)
                .email(email)
                .nickName(nickName)
                .number(number)
                .password(passwordEncoder.encode(password))
                .role(nickName.equalsIgnoreCase("admin") ? UserRole.ADMIN.name() : UserRole.USER.name())
                .build();

        return userRepository.save(user);
    }

    @Transactional
    public SiteUser updateUserProfile(String name, String email, String nickName, String number) {
        // 현재 로그인한 사용자의 정보 가져오기
<<<<<<< HEAD
        SiteUser userToUpdate = userRepository.findByEmail(email);
        if (userToUpdate != null) {
            // 정보 업데이트
            userToUpdate.setName(name);
            userToUpdate.setEmail(email);
            userToUpdate.setNickName(nickName);
            userToUpdate.setNumber(number);
            // 수정된 정보 저장
            return userRepository.save(userToUpdate);
        } else return null;
    }
=======
        Optional<SiteUser> userToUpdate = userRepository.findByEmail(email);
        if(userToUpdate.isPresent()) {
            SiteUser user = userToUpdate.get();
            // 정보 업데이트
            user.setName(name);
            user.setEmail(email);
            user.setNickName(nickName);
            user.setNumber(number);
            // 수정된 정보 저장
            return userRepository.save(user);
        } else return null;
    }

    public SiteUser userEmailCheck(String userEmail, String userName) {
        Optional<SiteUser> _user = userRepository.findByEmail(userEmail);

        if( _user.isPresent() && _user.get().getName().equals(userName))
            return _user.get();
        else
            return null;
    }
    public void changePassword(SiteUser siteUser, String password){
        siteUser.setPassword(passwordEncoder.encode(password));
        userRepository.save(siteUser);
    }
>>>>>>> aa24f00274921681faced4aa1b6aeccb532ff0a9
}
