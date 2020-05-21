package com.efes.quizApp.entity;


import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.scheduling.support.SimpleTriggerContext;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "question")
/*
@TypeDefs({
        @TypeDef(
                name = "string-array",
                typeClass = StringArrayType.class
        ),
        @TypeDef(
                name = "int-array",
                typeClass = IntArrayType.class
        )
})

 */

/*
@TypeDef(
        name = "string-array",
        typeClass = StringArrayType.class
)

 */

public class Question extends BaseEntity {
    /*
     {
         description: string,
                 difficulty: Difficulty,
             correctOption: Option,
             options: string[],
         tags: Tag[]
     }


     */


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "questionCode", unique = true)
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


    @Column(name = "optionA")
    private String optionA;

    @Column(name = "optionB")
    private String optionB;

    @Column(name = "optionC")
    private String optionC;

    @Column(name = "optionD")
    private String optionD;


/*
    @JoinColumn(name = "quizConnectId")
    @ManyToOne(fetch = FetchType.LAZY)
    // @Column(name = "quizId")
    private Long quizId;

 */

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;


    public Question(long i, String q1) {
        id = i;
        correct_option = q1;
    }
}
