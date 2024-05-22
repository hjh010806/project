package com.example.Project.List.Image;

import com.example.Project.Keys;
import com.example.Project.User.SiteUser;
import com.example.Project.User.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final UserService userService;
    private final ImageRepository imageRepository;


    @Transactional
    public String tempUpload(ImageDto imageDto, String username) {
        try {
            SiteUser siteUser = userService.getUser(username);
            File file = new File(Keys.uploadDir.getLocation() + siteUser.getImageUrl());
            if (file.exists())
                file.delete();

            UUID uuid = UUID.randomUUID();
            String imageFileName = "/List/" + username + "_" + uuid + imageDto.getFile().getOriginalFilename();

            Path imageFilePath = Paths.get(Keys.uploadDir.getLocation() + imageFileName);

            // 파일을 저장합니다.
            Files.write(imageFilePath, imageDto.getFile().getBytes());


            userService.setUrl(siteUser, imageFileName);
            // 사용자 이름을 기반으로 사용자 객체를 가져옵니다.
            return userService.getUserByEmailUrl(username);


        } catch (IOException e) {
            e.printStackTrace();
            // 파일 저장 중 에러가 발생하면 처리합니다.
            return "";
        }
    }

    @Transactional
    public String profileUserUpload(ImageDto imageDto, String username) {
        try {
            SiteUser siteUser = userService.getUser(username);
            File file = new File(Keys.uploadDir.getLocation() + siteUser.getProfileUrl());
            if (file.exists())
                file.delete();

            UUID uuid = UUID.randomUUID();
            String profileImageFileName = "/User/" + username + "_Profile_" + uuid + imageDto.getFile().getOriginalFilename();

            Path profileImageFilePath = Paths.get(Keys.uploadDir.getLocation() + profileImageFileName);

            Files.write(profileImageFilePath, imageDto.getFile().getBytes());

            userService.setProfileUrl(siteUser, profileImageFileName);

            return userService.getUserByEMailProfileUrl(username);

        } catch (IOException e) {
            e.printStackTrace();
            // 파일 저장 중 에러가 발생하면 처리합니다.
            return "";
        }

    }
}
