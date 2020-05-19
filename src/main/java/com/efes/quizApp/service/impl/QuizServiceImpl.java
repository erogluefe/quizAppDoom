package com.efes.quizApp.service.impl;

import com.efes.quizApp.dto.QuestionDto;
import com.efes.quizApp.dto.QuizDto;
import com.efes.quizApp.entity.Question;
import com.efes.quizApp.entity.Quiz;
import com.efes.quizApp.repository.QuestionRepository;
import com.efes.quizApp.repository.QuizRepository;
import com.efes.quizApp.service.QuizService;
import com.efes.quizApp.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;

    private final QuestionRepository questionRepository;


    private final ModelMapper modelMapper;


    public QuizServiceImpl(QuizRepository quizRepository, ModelMapper modelMapper, QuestionRepository questionRepository) {

        this.quizRepository = quizRepository;
        this.modelMapper = modelMapper;
        this.questionRepository = questionRepository;
    }


    @Override
    public QuizDto save(QuizDto quizDto) {

        Quiz quizCheck = quizRepository.getByName(quizDto.getName());

        if (quizCheck != null)
            throw new IllegalArgumentException("aynı quiz name var zaten");

        Quiz q = modelMapper.map(quizDto, Quiz.class);
        q = quizRepository.save(q);
        quizDto.setId(q.getId());
        return quizDto;
    }

    @Override
    public QuizDto getById(Long id) {
        Quiz q = quizRepository.getOne(id);
        return modelMapper.map(q, QuizDto.class);

    }

    @Override
    public QuizDto getByName(String questionCode) {
        return null;
    }

    @Override
    public List<QuizDto> getByNameContains(String questionCode) {
        return null;
    }

    @Override
    public TPage<QuizDto> getAllPageable(Pageable pageable) {

        Page<Quiz> data = quizRepository.findAll(pageable);
        TPage<QuizDto> response = new TPage<QuizDto>();
        response.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), QuizDto[].class)));
        return response;

    }


    @Override
    public Boolean delete(QuizDto project) {
        return null;
    }

    public Boolean delete(Long id) {

        quizRepository.deleteById(id);
        return true;


    }


    @Override
    public QuizDto update(Long id, QuizDto quizDto) {

        Quiz quizDb = quizRepository.getOne(id);
        if (quizDb == null)
            throw new IllegalArgumentException("bu kayıt db de yok ID: " + id);
        Quiz quizCheck = quizRepository.getByNameAndIdNot(quizDto.getName(), id);
        if (quizCheck != null)
            throw new IllegalArgumentException("aynı quiz name var zaten");


        quizDb.setDifficulty(quizDto.getDifficulty());

        quizDb.setType(quizDto.getType());
        quizDb.setTimeLimit(quizDto.getTimeLimit());
        quizDb.setName(quizDto.getName());
        quizDb.setResolvedId(quizDto.getResolvedId());
        quizDb.setCuratedId(quizDto.getCuratedId());
        quizDb.setPrivateOfId(quizDto.getPrivateOfId());

        quizRepository.save(quizDb);
        return modelMapper.map(quizDb, QuizDto.class);


    }

    @Override
    public QuizDto getAllQuizzes() {
        Quiz q = quizRepository.findSpecificquiz();
        return modelMapper.map(q, QuizDto.class);

    }

//    @Override
//    public Boolean addQuestion(Long Quesid, Long QuizId) {
//            quizRepository.getOne(QuizId).getQuestions().add(questionRepository.getOne(Quesid));
//            return true;
//
//    }
//
//    @Transactional
//    public Boolean addQuestions(Long id, Long qid){
//
//        Question question =questionRepository.getById(qid);
//        Question q = new Question();
//        q.setId(question.getId());
//
//        q.setQuestionCode(question.getQuestionCode());
//        q.setImageName(q.getImageName());
//        q.setDescription(question.getDescription());
//        q.setDifficulty(question.getDifficulty());
//        q.setCorrect_option(question.getCorrect_option());
//        q.setIs_public(question.getIs_public());
//        questionRepository.save(q);
//
//       // q.setQuiz(quizRepository.getOne(id));
//
//        //quizRepository.getOne(id).setQuestions(questions); // bu satıra check atmalı
//        System.out.println(Arrays.asList(quizRepository.getOne(id).getQuestions()));
//        System.out.println("efe ");
//
//
//        Quiz quiz = quizRepository.getOne(id);
//
//
//
//
//        return true;
//
//
//
//    }


}
