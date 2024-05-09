package com.example.Project.Likes;

import java.util.Optional;

public interface LikesCustom {
    Optional<Likes> findByListMainIdAndSiteUserId(Long listMainId, Long UserId);
}
