package com.efes.quizApp.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "question")
public class Question extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "questionCode",unique = true)
    private String questionCode;


    @Column(name = "imageName")
    private String imageName;

    @Column(name = "description", length = 400)
    //@NotNull
    private String description;

    //@NotNull
    @Column(name = "difficulty")
    private int difficulty;

    //@NotNull
    @Column(name = "correct_option")
    private String correct_option;

    //@NotNull
    @Column(name = "is_public")
    private int is_public;

    @JoinColumn(name = "quizConnectId")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Quiz quiz;




}
