package com.example.Project.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ListRepository extends JpaRepository<ListMain, Integer> {
    @Query("SELECT e FROM ListMain e ORDER BY e.createDate DESC")
    List<ListMain> findAllOrderByCreateDateDESC();

    List<ListMain> findByAuthorId(Long authorId);


}
