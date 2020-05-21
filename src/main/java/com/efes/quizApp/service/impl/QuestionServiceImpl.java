package com.efes.quizApp.service.impl;

import com.efes.quizApp.dto.QuestionDto;
import com.efes.quizApp.entity.Question;
import com.efes.quizApp.repository.QuestionRepository;
import com.efes.quizApp.repository.QuizRepository;
import com.efes.quizApp.service.QuestionService;
import com.efes.quizApp.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {


    private final QuestionRepository questionRepository;

    private final QuizRepository quizRepository;

    private final ModelMapper modelMapper;


    public QuestionServiceImpl(QuestionRepository questionRepository, ModelMapper modelMapper,QuizRepository quizRepository) {

        this.questionRepository = questionRepository;
        this.modelMapper = modelMapper;
        this.quizRepository=quizRepository;
    }


    @Override
    public QuestionDto save(QuestionDto questionDto) {

        Question questionCheck = questionRepository.getByQuestionCode(questionDto.getQuestionCode());

        if (questionCheck != null)
            throw new IllegalArgumentException("aynı question kod var zaten");

        Question q = modelMapper.map(questionDto, Question.class);

      //  q.setQuiz(quizRepository.getOne(questionDto.getId()));

        q = questionRepository.save(q);
        questionDto.setId(q.getId());
        return questionDto;
    }

    @Override
    public QuestionDto getById(Long id) {
        Question q = questionRepository.getOne(id);
        return modelMapper.map(q, QuestionDto.class);

    }

    @Override
    public QuestionDto getByQuestionCode(String questionCode) {
        return null;
    }

    @Override
    public List<QuestionDto> getByQuestionCodeContains(String questionCode) {
        return null;
    }

    @Override
    public TPage<QuestionDto> getAllPageable(Pageable pageable) {
        Page<Question> data = questionRepository.findAll(pageable);
        TPage<QuestionDto> response = new TPage<QuestionDto>();
        response.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), QuestionDto[].class)));
        return response;
    }

    public List<QuestionDto> getAll() {

        List<Question> data = questionRepository.findAll();
        return Arrays.asList(modelMapper.map(data, QuestionDto[].class));
    }


    @Override
    public Boolean delete(QuestionDto project) {
        return null;
    }

    public Boolean delete(Long id) {

        questionRepository.deleteById(id);
        return true;


    }


    @Override
    public QuestionDto update(Long id, QuestionDto questionDto) {

        Question questionDb = questionRepository.getOne(id);
        if (questionDb == null)
            throw new IllegalArgumentException("bu kayıt db de yok ID: " + id);

        //  Project projectCheck = projectRepository.getByProjectCode(project.getProjectCode());

        Question questionCheck= questionRepository.getByQuestionCodeAndIdNot(questionDto.getQuestionCode(),id);
        // alttaki satır bir şeyler değişecek ve üsttekiyle birleşecek.
        if (questionCheck!=null)
            throw new IllegalArgumentException("aynı question kod var zaten");


        //  if (projectCheck != null && projectCheck.getId() !=projectDb.getId())
        //   throw new IllegalArgumentException("aynı project kod var zaten");



        questionDb.setQuestionCode(questionDto.getQuestionCode());
        questionDb.setImageName(questionDto.getImageName());
        questionDb.setDescription(questionDto.getDescription());
        questionDb.setDifficulty(questionDto.getDifficulty());
        questionDb.setCorrect_option(questionDto.getCorrect_option());
        questionDb.setIs_public(questionDto.getIs_public());


        questionRepository.save(questionDb);
        return modelMapper.map(questionDb, QuestionDto.class);



    }

    @Override
    public QuestionDto getAllQuestions() {
        Question q = questionRepository.findSpecificQuestion();
        return modelMapper.map(q, QuestionDto.class);

    }




}

