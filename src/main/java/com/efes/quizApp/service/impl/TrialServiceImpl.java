package com.efes.quizApp.service.impl;

import com.efes.quizApp.dto.TrialDto;
import com.efes.quizApp.dto.WrapTrialDto;
import com.efes.quizApp.entity.Trial;
import com.efes.quizApp.entity.TrialId;
import com.efes.quizApp.repository.TrialRepository;
import com.efes.quizApp.service.TrialService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TrialServiceImpl implements TrialService {

    private ModelMapper modelMapper;
    private TrialRepository trialRepo;

    private TrialServiceImpl(TrialRepository trialRepo, ModelMapper modelMapper){
        this.modelMapper = modelMapper;
        this.trialRepo = trialRepo;
    }

    @Override
    public TrialDto save(TrialDto trialInfo) {
        Trial checkTrial = trialRepo.getByDevIdAndQuizIdAndTrialNo(trialInfo.getDevId(), trialInfo.getQuizId(), trialInfo.getTrial_no());

        // Does Trial exist already?
        if(checkTrial != null){
            throw new IllegalArgumentException("Trial var zaten");
        }

        // if not, add it to DB.
        Trial newTrial = modelMapper.map(trialInfo, Trial.class);
        trialRepo.save(newTrial);

        return trialInfo;
    }

    @Override
    public Boolean delete(TrialDto trialDto) {
        Trial trial = modelMapper.map(trialDto, Trial.class);
        return trialRepo.deleteByDevIdAndQuizIdAndTrialNo(trial.getDevId(), trial.getQuizId(), trial.getTrialNo());
    }

    @Override
    public TrialDto getById(Long devId, Long quizId, int noTrials) {
        Trial trial = trialRepo.getByDevIdAndQuizIdAndTrialNo(devId,quizId,noTrials);
        if(trial == null)
            throw new IllegalArgumentException("Verilen IDli trial yok");
        return modelMapper.map(trial, TrialDto.class);
    }

    @Override
    public WrapTrialDto getTrials(Long quizId, Long devId) {
        List<Trial> trials = trialRepo.getByQuizIdAndDevId(quizId,devId);
        List<TrialDto> dtoList = new ArrayList<TrialDto>();

        for (Trial trial: trials) {
            TrialDto trialDto = modelMapper.map(trial, TrialDto.class);
            dtoList.add(trialDto);
        }

        WrapTrialDto wrap =  new WrapTrialDto(dtoList);
        return wrap;
    }

    @Override
    public WrapTrialDto getAllDevTrials(Long devId) {
        List<Trial> trials = trialRepo.getByDevId(devId);
        List<TrialDto> dtoList = new ArrayList<TrialDto>();

        for (Trial trial: trials) {
            TrialDto trialDto = modelMapper.map(trial, TrialDto.class);
            dtoList.add(trialDto);
        }

        WrapTrialDto wrap =  new WrapTrialDto(dtoList);
        return wrap;
    }

    @Override
    public WrapTrialDto getQuizTrials(Long quizId) {
        List<Trial> trials = trialRepo.getByQuizIdOrderBySuccessRateAsc(quizId);
        List<TrialDto> dtoList = new ArrayList<TrialDto>();

        for (Trial trial: trials) {
            TrialDto trialDto = modelMapper.map(trial, TrialDto.class);
            dtoList.add(trialDto);
        }
        WrapTrialDto wrap =  new WrapTrialDto(dtoList);
        return wrap;
    }
}
