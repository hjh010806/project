package com.example.Project.List;

import com.example.Project.User.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ListRepository extends JpaRepository<ListMain, Integer> {
    @Query("SELECT e FROM ListMain e ORDER BY e.createDate DESC")
    List<ListMain> findAllOrderByCreateDateDesc();

}
