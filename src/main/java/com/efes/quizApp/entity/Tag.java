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
@Table(name = "tag")
public class Tag implements Serializable {



    @Id
    @Column(name = "tagNamee")
    private String tagNamee;




}
