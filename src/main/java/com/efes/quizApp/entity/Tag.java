package com.efes.quizApp.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "tag")
public class Tag implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

 //   @ManyToMany(mappedBy = "tags")
    @Column(name = "tagName",nullable = false)
    private String tagName;


  //  @ManyToMany(mappedBy = "tags")
  //  private List<Quiz> quizzes;


}
