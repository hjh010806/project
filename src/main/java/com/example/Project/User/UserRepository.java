package com.example.Project.User;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
    SiteUser findByEmail(String email);

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
}
