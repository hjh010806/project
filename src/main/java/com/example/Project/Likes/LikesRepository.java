package com.example.Project.Likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface LikesRepository extends JpaRepository<Likes, Integer>, LikesCustom {
    
}
