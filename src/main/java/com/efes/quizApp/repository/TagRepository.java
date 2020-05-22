package com.efes.quizApp.repository;


import com.efes.quizApp.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface TagRepository extends JpaRepository<Tag,Long> {


    @Transactional
    @Query(value = "SELECT * FROM tag ", nativeQuery = true)
    List<Tag> selectAllTagsNativeSql();






}
