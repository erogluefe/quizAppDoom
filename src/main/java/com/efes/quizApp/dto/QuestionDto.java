package com.efes.quizApp.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {



    private Long id;

    private String questionCode;

    private String imageName;

    private String description;

    private int difficulty;

    private String correct_option;

    private int is_public;

    private String optionA;

    private String optionB;

    private String optionC;

    private String optionD;

  //  public String[] answers;

   // private String answers;

   // private String[] answers;


    private QuizDto quiz;

}
