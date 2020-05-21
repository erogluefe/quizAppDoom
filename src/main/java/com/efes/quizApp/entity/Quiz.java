package com.efes.quizApp.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "quiz")
public class Quiz {

    /*
    constraints:
    not null
    unique
    primary key
    foreign key

     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "quizId",unique = true)
    private int quizId;

    @Column(name = "difficulty",nullable = false)
    private int difficulty;

    @Column(name = "type")
    private String type;

    @Column(name = "timeLimit")
    private int timeLimit;

    @Column(name = "name",nullable = false,unique = true)
    private String name;

    @Column(name = "ResolvedId") // buda nullabalce olacak ama mantığını tam anlamak lazım.
    private Long resolvedId; // fk to admin Id

    @Column(name = "curatedID")
    private int curatedId; // fk to adminId

    @Column(name = "privateOfId")
    private int privateOfId; // fk to companyId


    @JoinColumn(name = "quizConnectId")
    @OneToMany(fetch = FetchType.LAZY)
  //  @Column(name = "questions")
    private List<Question> questions;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "quiz_tag_fk",
            joinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "quiz_Id", referencedColumnName = "id"))
    private List<Tag> tags;








}
