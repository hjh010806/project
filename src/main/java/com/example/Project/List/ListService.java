package com.example.Project.List;

import com.example.Project.Main.DataNotFoundException;
import com.example.Project.User.SiteUser;
import com.example.Project.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class ListService {
    private final ListRepository listRepository;
    private final UserRepository userRepository;


    public List<ListMain> getList() {
        return this.listRepository.findAll();
    }
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

    public void modify(ListMain listMain, String content) {
        listMain.setContent(content);
        listMain.setCreateDate(LocalDateTime.now());
        this.listRepository.save(listMain);
    }

    public void delete(ListMain listMain) {
        this.listRepository.delete(listMain);
    }

    public List<ListMain> getAuthorList(Integer id) {
        return listRepository.findByAuthorId(id);
    }

//    public void vote(ListMain listMain, SiteUser siteUser) {
//        listMain.getVoter().add(siteUser);
//        this.listRepository.save(listMain);
//    }
//
//    public void deleteVote(ListMain listMain, SiteUser siteUser) {
//        listMain.getVoter().remove(siteUser);
//    }

}
