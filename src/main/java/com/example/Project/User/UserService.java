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
        SiteUser user = new SiteUser();
        user.setName(name);
        user.setEmail(email);
        user.setNickName(nickName);
        user.setNumber(number);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(nickName.equalsIgnoreCase("admin") ? UserRole.ADMIN.name() : UserRole.USER.name());
        userRepository.save(user);
        return user;
    }

}
