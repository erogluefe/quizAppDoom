package com.efes.quizApp.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "consistOf")
public class ConsistOf extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "quizId",nullable = false)
    private Long quizId;

    @Column(name = "questionId",nullable = false)
    private Long questionId;

    @Column(name = "orderCons",nullable = false)
    private Long orderCons;

}
