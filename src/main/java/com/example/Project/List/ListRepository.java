package com.example.Project.List;

import com.example.Project.User.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ListRepository extends JpaRepository<ListMain, Integer> {
    @Query("SELECT e FROM ListMain e ORDER BY e.createDate DESC")
    List<ListMain> findAllOrderByCreateDateDesc();

    List<ListMain> findByAuthorId(Integer authorId);

}
