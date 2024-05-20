package com.example.Project.List.Image;

import com.example.Project.List.ListMain;
import com.example.Project.User.SiteUser;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    private SiteUser siteUser;


    public Image(String imageUrl, SiteUser siteUser) {
        this.imageUrl = imageUrl;
        this.siteUser = siteUser;
    }
}
