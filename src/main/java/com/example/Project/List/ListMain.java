package com.example.Project.List;

import com.example.Project.Answer.Answer;
import com.example.Project.Likes.Likes;
import com.example.Project.User.SiteUser;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class ListMain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @ManyToOne
    private SiteUser author;

    @OneToMany(mappedBy = "listMain", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

    @OneToMany(mappedBy = "listMain", cascade = CascadeType.ALL)
    private Set<Likes> likes;

    @Builder
    public ListMain(String content) {
        this.content = content;
        this.createDate = LocalDateTime.now();
    }

}
