package com.example.Project.User;


import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
    SiteUser findByEmail(String email);

    SiteUser findByName(String name);

}
