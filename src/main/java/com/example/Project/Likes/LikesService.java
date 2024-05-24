package com.example.Project.Likes;

import com.example.Project.List.ListService;
import com.example.Project.User.SiteUser;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LikesService {

    private final LikesRepository likesRepository;
    private final ListService listService;
    @Transactional
    public void createLikes(int listMainId, SiteUser siteUser) {
        Likes likes = new Likes();
        likes.setListMain(listService.getListMain(listMainId));
        likes.setSiteUser(siteUser);
        likesRepository.save(likes);
    }

    @Transactional
    public void deleteLikes(long listMainId, long principalId) {
        Likes likes= likesRepository.findByListMainIdAndSiteUserId(listMainId, principalId).get();
        likesRepository.delete(likes);
    }

    public boolean isLikes(long listMainId, long siteUserId) {
        Optional<Likes> likes = likesRepository.findByListMainIdAndSiteUserId(listMainId, siteUserId);
        return likes.isPresent();
    }

}
