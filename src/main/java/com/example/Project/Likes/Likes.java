package com.example.Project.Likes;

import com.example.Project.List.ListMain;
import com.example.Project.User.SiteUser;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "likes_uk",  // 제약조건 이름
                        columnNames = {"listMainId", "siteUserId"} // 제약조건에 사용할 실제 데이터베이스 컬럼명
                )
        }
)
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @JoinColumn(name = "listMainId")
    @ManyToOne
    private ListMain listMain;      // 좋아요할 게시물

    @JoinColumn(name = "siteUserId")
    @ManyToOne
    private SiteUser siteUser; // 좋아요한 유저

    private LocalDateTime createDate; // 입력된 시간


    @PrePersist
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

}
