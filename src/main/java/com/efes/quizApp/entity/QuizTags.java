package com.efes.quizApp.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "quizTags")
public class QuizTags implements Serializable {


    @Column(name = "quizId")
    private Long quizId;

    @Id
    @Column(name = "tag")
    private String tag;
}
