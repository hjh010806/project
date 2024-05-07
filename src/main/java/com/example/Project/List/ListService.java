package com.example.Project.List;

import com.example.Project.Main.DataNotFoundException;
import com.example.Project.User.SiteUser;
import com.example.Project.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class ListService {
    private final ListRepository listRepository;
    private final UserRepository userRepository;


    //리스트 생성
    public void create(String content, SiteUser user) {
        ListMain listMain = new ListMain();
        listMain.setContent(content);
        listMain.setCreateDate(LocalDateTime.now());
        listMain.setAuthor(user);
        this.listRepository.save(listMain);
    }

    public ListMain getListMain(Integer id) {
        Optional<ListMain> listMain = this.listRepository.findById(id);
        if (listMain.isPresent())
            return listMain.get();
        else
            throw new DataNotFoundException("listMain not found");
    }

}
