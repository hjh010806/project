package com.example.Project.List.Image;

import com.example.Project.User.SiteUser;
import com.example.Project.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;
    private final UserService userService;

    @PostMapping("/image/load")
    public String imageUpload(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        String imageUrl = userService.getUserByEmail(username);
        model.addAttribute("imageUrl", imageUrl);
        System.out.println("123");
        return "redirect:/home/createList";
    }

    @PostMapping("/image/temp")
    public String tempUpload(ImageDto imageDto,Model model, @AuthenticationPrincipal UserDetails userDetails, RedirectAttributes redirectAttributes) {
        if (userDetails == null) {
            // 사용자 정보가 없는 경우 처리
            return "login/login_form"; // 로그인 페이지로 리디렉션
        }
        // 이미지를 업로드합니다.
        String temp =  imageService.tempUpload(imageDto, userDetails.getUsername());
        // 사용자 이름을 기반으로 리다이렉트합니다.
        redirectAttributes.addFlashAttribute("temp",temp);
        return "redirect:/home/createList";
    }

    @Value("${image.upload.dir}")
    private String uploadDir;
    @GetMapping("/{filename}")
    public ResponseEntity<byte[]> getImage(@PathVariable String filename) {
        try {
            Path imagePath = Paths.get(uploadDir, filename);
            File file = imagePath.toFile();
            byte[] data = Files.readAllBytes(file.toPath());
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(data);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}
