package com.efes.quizApp.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "users", indexes = {@Index(name = "emailInd", columnList = "email")})
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name= "uname",length = 100,unique = true,nullable = false)
    private String username;

    @Column(name = "email",length = 100,nullable = false)
    private String email;


    @Column(name = "pwd",length = 200,nullable = false)
    private String password;

    @Column(name = "name_surname",length = 200)
    private String nameSurname;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "description")
    private  String description;

    @Column(name = "role",nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;


    @Column(name = "contactInfo")
    private String contactInfo;

    @Column(name = "jobSituation")
    private JobSituation situation;


    @Column(name = "image")
    private String image;


    @Column(name = "bannedBy")
    private String bannedBy;


    @Column(name = "cv")
    private String cv;







    /*
    @JoinColumn(name = "assignee_user_id")
    @OneToMany(fetch = FetchType.LAZY)
    private List<Issue> issues;

     */
}

