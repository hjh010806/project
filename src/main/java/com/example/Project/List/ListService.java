package com.example.Project.List;

import com.example.Project.List.Image.ImageDto;
import com.example.Project.List.Image.ImageService;
import com.example.Project.Main.DataNotFoundException;
import com.example.Project.User.SiteUser;
import com.example.Project.User.UserRepository;
import com.example.Project.User.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.source.spi.PluralAttributeIndexNature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class ListService {
    private final ListRepository listRepository;
    private final UserService userService;
    private final ImageService imageService;
    @Value("${image.upload.dir}")
    private String uploadDir;


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
        SiteUser user = userService.getUser(listMain.getAuthor().getEmail());
        deleteListUrl(listMain);
        listMain.setContent(content);
        listMain.setCreateDate(LocalDateTime.now());
        listMain.setListUrl(user.getImageUrl());
        userService.deleteUrl(user);
        this.listRepository.save(listMain);
    }

    public void deleteListUrl(ListMain listMain) {
        File file = new File(uploadDir+listMain.getListUrl());
        if(file.exists()) {
            file.delete();
            listMain.setListUrl(null);
        }
    }


    public void delete(ListMain listMain) {
        File file = new File(uploadDir+listMain.getListUrl());
        if(file.exists()) {
            file.delete();
            listMain.setListUrl(null);
        }
        this.listRepository.delete(listMain);
    }

    public List<ListMain> getAuthorList(Long id) {
        return listRepository.findByAuthorId(id);
    }

}
