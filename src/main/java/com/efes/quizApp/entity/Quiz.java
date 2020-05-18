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

    QuizID int,
difficulty numeric(1,2) not null,
type varchar(20),
time_limit time,
name varchar(20) not null,
ResolvesID int not null,
CuratedID int,
PrivateOfID int,
primary key(QuizID),
foreign key(ResolvesID) references Admin(ID),
foreign key (CuratedID) references Admin(ID),
foreign key (PrivateOfID) references Company(ID));
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "quizId",unique = true,nullable = false)
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
    private int resolvedId; // fk to admin Id

    @Column(name = "curatedID")
    private int curatedId; // fk to adminId

    @Column(name = "privateOfId")
    private int privateOfId; // fk to companyId



    @JoinColumn(name = "quizConnectId")
    @OneToMany(fetch = FetchType.LAZY)
    private List<Question> questions;








}
