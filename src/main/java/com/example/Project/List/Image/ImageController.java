package com.example.Project.List.Image;

import com.example.Project.Keys;
import com.example.Project.User.SiteUser;
import com.example.Project.User.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.jaxb.mapping.JaxbCompositeUserTypeRegistration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/image/temp")
    public String tempUpload(ImageDto imageDto, @AuthenticationPrincipal UserDetails userDetails, RedirectAttributes redirectAttributes, @RequestParam(value = "temp_text") String text, @RequestParam(value = "destination") String destination) {
        // 사용자 정보가 없는 경우 처리
        if (userDetails == null)
            return "login/login_form"; // 로그인 페이지로 리디렉션

        // 이미지를 업로드합니다.
        String temp = imageService.tempUpload(imageDto, userDetails.getUsername());
        // 사용자 이름을 기반으로 리다이렉트합니다.
        redirectAttributes.addFlashAttribute("temp", temp);

        redirectAttributes.addFlashAttribute("text", text);
        return "redirect:/home/" + destination;
    }

    @PostMapping("/profileImage/temp")
    public String tempUpload(ImageDto imageDto, @AuthenticationPrincipal UserDetails userDetails, RedirectAttributes redirectAttributes) {
        if (userDetails == null)
            return "login/login_form";

        String temp_profile = imageService.profileUserUpload(imageDto, userDetails.getUsername());
        return "redirect:/user/profile";
    }

    @GetMapping("/{filename}")
    public ResponseEntity<byte[]> getImage(@PathVariable String filename) {
        try {
            Path imagePath = Paths.get(Keys.uploadDir.getLocation(),filename);
            File file = imagePath.toFile();
            byte[] data = Files.readAllBytes(file.toPath());
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(data);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/image/delete")
    public String imageDelete(@AuthenticationPrincipal UserDetails userDetails) {
        SiteUser user = userService.getUser(userDetails.getUsername());
        userService.deleteUrlFile(user);

        return "redirect:/";
    }
}
