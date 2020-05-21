package com.efes.quizApp.service.impl;

import com.efes.quizApp.dto.ConsDto;
import com.efes.quizApp.dto.QuestionDto;
import com.efes.quizApp.entity.ConsistOf;
import com.efes.quizApp.entity.Question;
import com.efes.quizApp.repository.ConsistOfRepository;
import com.efes.quizApp.repository.QuestionRepository;
import com.efes.quizApp.service.ConsistOfService;
import com.efes.quizApp.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ConsistOfServiceImpl implements ConsistOfService {

    private final ConsistOfRepository consistOfRepository;

    private final QuestionRepository questionRepository;

    private final ModelMapper modelMapper;


    public ConsistOfServiceImpl(ConsistOfRepository consistOfRepository,ModelMapper modelMapper,QuestionRepository questionRepository) {
        this.consistOfRepository = consistOfRepository;
        this.modelMapper=modelMapper;
        this.questionRepository=questionRepository;
    }

    @Override
    public ConsDto save(ConsDto consDto) {

        ConsistOf q = modelMapper.map(consDto, ConsistOf.class);
        q = consistOfRepository.save(q);
        consDto.setId(q.getId());
        return consDto;


    }

    @Override
    public Boolean delete(ConsDto consDto) {

        consistOfRepository.deleteById(consDto.getId());
        return true;
    }


    public Boolean delete(Long id) {

        consistOfRepository.deleteById(id);
        return true;


    }
    public  void deleteWithSqlQuer(Long id){
       // return consistOfRepository.deleteAllByQuestionId(id);
        consistOfRepository.deleteSpecificQuestions(id);

    }


    @Override
    public ConsDto update(Long id, ConsDto consDto) {
        ConsistOf constdb = consistOfRepository.getOne(id);
        if (constdb == null)
            throw new IllegalArgumentException("bu kayıt db de yok ID: " + id);


        constdb.setQuestionId(consDto.getQuestionId());
        constdb.setQuizId(consDto.getQuizId());
        constdb.setOrderCons(consDto.getOrderCons());


        consistOfRepository.save(constdb);
        return modelMapper.map(constdb, ConsDto.class);

    }

    @Override
    public ConsDto getAllQuestions() {
        return null;
    }

    @Override
    public ConsDto getById(Long id) {
        ConsistOf c = consistOfRepository.getOne(id);
        return modelMapper.map(c, ConsDto.class);



    }
    public List<QuestionDto> getAlll(Long id) {

        //Long quizId = consistOfRepository.getByQuizId(id).getQuizId();
        List<ConsistOf> questionsInCons = consistOfRepository.getByQuizId(id);

        List<Question> questions= new ArrayList<>();

        for(ConsistOf e :questionsInCons){
            Question q = questionRepository.getById(e.getQuestionId());
            questions.add(q);
        }

        return Arrays.asList(modelMapper.map(questions, QuestionDto[].class));
    }





    @Override
    public List<Question> getAllQuestionsInQuiz(Long quizId) {
        return null;
    }

    @Override
    public ConsDto getByQuestionCode(String questionCode) {
        return null;
    }

    @Override
    public List<ConsDto> getByQuestionCodeContains(String questionCode) {
        return null;
    }

    @Override
    public TPage<ConsDto> getAllPageable(Pageable pageable) {
        return null;
    }
}
