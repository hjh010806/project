package com.example.Project.User;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


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

    Optional<SiteUser> findByNickName(String nickName);

    Optional<SiteUser> findByPassword(String password);


    @Query("select "
            + "distinct u "
            + "from SiteUser u "
            + "where "
            + "u.name like %:kw% "
            + "or u.nickName like %:kw% "
            + "or u.email like :kw "
    )
    List<SiteUser> findAllByKeyword(@Param("kw") String kw);

}
