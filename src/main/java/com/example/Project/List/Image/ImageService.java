package com.example.Project.List.Image;

import com.example.Project.List.ListMain;
import com.example.Project.User.SiteUser;
import com.example.Project.User.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    @Value("${image.upload.dir}")
    private String uploadDir;

    @Transactional
    public void imageUpload(ImageDto imageDto, String username) {
        try {
            UUID uuid = UUID.randomUUID();
            String imageFileName = username + "_" + uuid;
            String uploadFolder = uploadDir;
            Path imageFilePath = Paths.get(uploadFolder + imageFileName);

            Files.write(imageFilePath, imageDto.getFile().getBytes());

            SiteUser user = userService.getUser(username);

            Image image = new Image(imageFileName, user);
            Image saveImage = imageRepository.save(image);

            userService.setUrl(user, imageFileName);

            System.out.println(saveImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public String tempUpload(ImageDto imageDto, String username) {
        try {
            UUID uuid = UUID.randomUUID();
            String imageFileName = "/" + username + "_" + uuid + imageDto.getFile().getOriginalFilename();
            String uploadFolder = uploadDir;
            Path imageFilePath = Paths.get(uploadFolder+imageFileName);

            // 파일을 저장합니다.
            Files.write(imageFilePath, imageDto.getFile().getBytes());

            SiteUser siteUser = userService.getUser(username);
            userService.setUrl(siteUser,imageFileName);
            // 사용자 이름을 기반으로 사용자 객체를 가져옵니다.
            return userService.getUserByEmail(username);


        } catch (IOException e) {
            e.printStackTrace();
            // 파일 저장 중 에러가 발생하면 처리합니다.
            return "";
        }
    }
}
