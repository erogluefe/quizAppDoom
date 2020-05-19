package com.efes.quizApp.repository;

import com.efes.quizApp.entity.Question;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.sql.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {


    Question getById(Long id);

    Question getByQuestionCode(String id);

    List<Question> getByQuestionCodeContains(String description);



    Question getByQuestionCodeAndIdNot(String questionCode, Long id);

    Page<Question> findAll(Pageable pageable);

    List<Question> findAll(Sort sort);

//    @Query(value= " ",nativeQuery = true);
//    @NamedNativeQueries({
//            @NamedNativeQuery(name = "selectAuthorNames", query = "SELECT a.firstname, a.lastname FROM Author a"),
//            @NamedNativeQuery(name = "selectAuthorEntities", query = "SELECT a.id, a.version, a.firstname, a.lastname FROM Author a", resultClass = Author.class),
//            @NamedNativeQuery(name = "selectAuthorValue", query = "SELECT a.id, a.firstname, a.lastname, count(b.id) as numBooks FROM Author a JOIN BookAuthor ba on a.id = ba.authorid JOIN Book b ON b.id = ba.bookid GROUP BY a.id", resultSetMapping = "AuthorValueMapping")
//    })

    @Query(
            value = "SELECT * FROM question u WHERE u.id = 8",
            nativeQuery = true)
    Question findSpecificQuestion();







}
