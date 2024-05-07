package com.example.Project.Answer;

import com.example.Project.List.ListMain;
import com.example.Project.User.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    public Answer create(ListMain listMain, String content, SiteUser author) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setListMain(listMain);
        answer.setAuthor(author);
        this.answerRepository.save(answer);
        return answer;

    }

}
