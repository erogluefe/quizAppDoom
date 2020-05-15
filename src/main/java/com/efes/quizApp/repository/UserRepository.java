package com.efes.quizApp.repository;

import com.efes.quizApp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository

public interface UserRepository extends JpaRepository<User, Long> {


    User getById(Long id);

    User getByUsername(String id);

    List<User> getByUsernameContains(String description);

    User getByUsernameAndIdNot(String questionCode, Long id);

    Page<User> findAll(Pageable pageable);

    List<User> findAll(Sort sort);



    @Query(
            value = "SELECT * FROM users u WHERE u.id = 8",
            nativeQuery = true)
    User findSpecificUser();
}
