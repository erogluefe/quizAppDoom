package com.efes.quizApp.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto {


    private Long id;

    private int quizId;

    private int difficulty;

    private String type;

    private int timeLimit;

    private String name;

    private Long resolvedId; // fk to admin Id

    private int curatedId; // fk to adminId

    private int privateOfId; // fk to companyId

    private List<QuestionDto> questionDtos;


}
