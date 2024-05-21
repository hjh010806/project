package com.example.Project.List;

import com.example.Project.Main.DataNotFoundException;
import com.example.Project.User.SiteUser;
import com.example.Project.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class ListService {
    private final ListRepository listRepository;
    private final UserService userService;


    public List<ListMain> getList() {
        return this.listRepository.findAllOrderByCreateDateDESC();
    }
    //리스트 생성
    public void create(String content, SiteUser user) {
        ListMain listMain = new ListMain();
        listMain.setContent(content);
        listMain.setCreateDate(LocalDateTime.now());
        listMain.setAuthor(user);
        listMain.setListUrl(user.getImageUrl());
        userService.deleteUrl(user);
        this.listRepository.save(listMain);
    }

    public ListMain getListMain(Integer id) {
        Optional<ListMain> listMain = this.listRepository.findById(id);
        if (listMain.isPresent())
            return listMain.get();
        else
            throw new DataNotFoundException("listMain not found");
    }

    public void modify(ListMain listMain, String content) {
        listMain.setContent(content);
        listMain.setCreateDate(LocalDateTime.now());
        this.listRepository.save(listMain);
    }

    public void delete(ListMain listMain) {
        this.listRepository.delete(listMain);
    }

    public List<ListMain> getAuthorList(Long id) {
        return listRepository.findByAuthorId(id);
    }

}
