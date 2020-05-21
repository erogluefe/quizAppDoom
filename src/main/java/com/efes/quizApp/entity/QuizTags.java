package com.efes.quizApp.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "quizTagss")
public class QuizTags implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "tagQuizId")
    private Long tagQuizId;


    @Column(name = "tag")
    private String tag;
}
