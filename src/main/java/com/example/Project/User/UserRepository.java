package com.example.Project.User;


import com.example.Project.List.ListMain;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import static com.example.Project.User.QSiteUser.siteUser;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
//    SiteUser findByEmail(String email);

    Optional<SiteUser> findByEmail(String email);

    SiteUser findByName(String name);
    Optional<SiteUser> findById(Long id);
@Query("select u " +
        "from SiteUser u " +
        "where " +
        "  number = :number " +
        "or " +
        "  nickName = :nickName " +
        "or " +
        "  email = :email ")
    List<SiteUser> checklist(String number, String nickName, String email);

    Optional<ListMain> findByNickName(String nickName);

    Optional<SiteUser> findByPassword(String password);

}
