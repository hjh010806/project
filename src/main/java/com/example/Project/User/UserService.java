package com.example.Project.User;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String name, String email, String password, String nickName, String number){
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


}
