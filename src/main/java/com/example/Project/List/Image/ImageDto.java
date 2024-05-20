package com.example.Project.List.Image;

import com.example.Project.List.ListMain;
import com.example.Project.User.SiteUser;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Data
public class ImageDto {
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }
    public ImageDto(MultipartFile file) {
        this.file = file;
    }

    public Image toEntity(String imageUrl, SiteUser siteUser) {
        return Image.builder()
                .imageUrl(imageUrl)
                .siteUser(siteUser)
                .build();
    }
}
