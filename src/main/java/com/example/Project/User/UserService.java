package com.example.Project.User;

import com.example.Project.List.ListMain;
import com.example.Project.List.ListRepository;
import com.example.Project.Main.DataNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ListRepository listRepository;

    public boolean hasUser(String number, String nickName, String email) {
        return userRepository.checklist(number, nickName, email).size() > 1;
    }

    public SiteUser createUser(String name, String email, String password, String nickName, String number) {
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
        Optional<SiteUser> userToUpdate = userRepository.findByEmail(email);
        if(userToUpdate.isPresent()) {
            SiteUser user = userToUpdate.get();
            // 정보 업데이트
            user.setName(name);
            user.setEmail(email);
            user.setNickName(nickName);
            user.setNumber(number);
//            if(password.trim().length() != 0 && user.getPassword() != password){
//               user.setPassword(passwordEncoder.encode(password));
//            }

            // 수정된 정보 저장
            return userRepository.save(user);
        } else return null;
    }

    @Transactional
    public SiteUser updateUserPassword(Long userId, String currentPassword, String newPassword) {
        // 사용자 식별자를 기반으로 사용자를 가져옴
        Optional<SiteUser> userToUpdate = userRepository.findById(userId);

        if (userToUpdate.isPresent()) {
            SiteUser user = userToUpdate.get();

            // 현재 비밀번호가 일치하는지 확인
            if (passwordEncoder.matches(currentPassword, user.getPassword())) {
                // 새로운 비밀번호를 해싱하여 설정
                user.setPassword(passwordEncoder.encode(newPassword));

                // 사용자 엔터티를 저장하여 변경 사항을 데이터베이스에 반영
                return userRepository.save(user);
            } else {
                throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
            }
        } else {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
        }
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

    public SiteUser getUser(String email) {
        Optional<SiteUser> siteUser = this.userRepository.findByEmail(email);
        if (siteUser.isPresent())
            return siteUser.get();
        else
            throw new DataNotFoundException("siteuser not found");
    }
    public SiteUser getUserId(Long id) {
        Optional<SiteUser> siteUser = this.userRepository.findById(id);
        if (siteUser.isPresent())
            return siteUser.get();
        else
            throw new DataNotFoundException("siteuser not found");
    }


    public List<SiteUser> searchByKeyword(String kw) {
        return this.userRepository.findAllByKeyword(kw);
    }

}
