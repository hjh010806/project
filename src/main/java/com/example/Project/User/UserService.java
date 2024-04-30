package com.example.Project.User;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
}
