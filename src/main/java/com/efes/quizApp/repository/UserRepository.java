package com.efes.quizApp.repository;

import com.efes.quizApp.entity.ConsistOf;
import com.efes.quizApp.entity.Role;
import com.efes.quizApp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository

public interface UserRepository extends JpaRepository<User, Long> {


    @Transactional
    @Query(value = "SELECT * FROM users u WHERE u.id = :idOfUser", nativeQuery = true)
    User selectOneNativeSql(@Param("idOfUser") Long idOfUser);

    @Modifying
    @Query(value = "insert into users (uname,email,password,role) VALUES (:userName,:emailUser,:userPass,:roleOfUser)", nativeQuery = true)
    @Transactional
    void addByUserNativeSql(@Param("userName") String userName, @Param("emailUser") String emailUser, @Param("userPass") String userPass, @Param("roleOfUser") Role roleOfUser);


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM users  u WHERE u.id = :ifOfUser ",nativeQuery = true)
    void deleteByUserNativeSql(@Param("ifOfUser") Long ifOfUser);


    User getById(Long id);

    User getByUsername(String id);

    User getByEmail(String mail);

    List<User> getByUsernameContains(String description);

    User getByUsernameAndIdNot(String questionCode, Long id);

    Page<User> findAll(Pageable pageable);

    List<User> findAll(Sort sort);



    @Query(
            value = "SELECT * FROM users u WHERE u.id = 8",
            nativeQuery = true)
    User findSpecificUser();
}
