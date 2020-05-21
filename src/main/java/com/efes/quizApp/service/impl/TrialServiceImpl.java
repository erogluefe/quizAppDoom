package com.efes.quizApp.service.impl;

import com.efes.quizApp.dto.TrialDto;
import com.efes.quizApp.dto.WrapDto;
import com.efes.quizApp.entity.Question;
import com.efes.quizApp.entity.Quiz;
import com.efes.quizApp.entity.Trial;
import com.efes.quizApp.repository.QuizRepository;
import com.efes.quizApp.repository.TrialRepository;
import com.efes.quizApp.service.TrialService;

import org.modelmapper.ModelMapper;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class TrialServiceImpl implements TrialService {

    private ModelMapper modelMapper;
    private TrialRepository trialRepo;
    private QuizRepository quizRepo;

    private TrialServiceImpl(TrialRepository trialRepo, ModelMapper modelMapper, QuizRepository quizRepo){
        this.modelMapper = modelMapper;
        this.trialRepo = trialRepo;
        this.quizRepo = quizRepo;
    }

    @Override
    public TrialDto save(TrialDto trialInfo) {
        /*
        devId, quizId ve choosen_options var elimde
         */

        //checking whether user has passed the test o a prev trial
        //initializing trial no
        List<Trial> prevTrials = trialRepo.getByQuizIdAndDevId(trialInfo.getQuizId(), trialInfo.getDevId());
        int newTrNo = 0;
        for (Trial trial: prevTrials) {
            if(trial.getTrialNo() > newTrNo)
                newTrNo = trial.getTrialNo();
            if(trial.isPassed())
                throw new IllegalArgumentException("User has already passed the test before");
        }
        newTrNo++;
        // checking whether it is 3rd trial
        if(newTrNo > 3){
            throw new IllegalArgumentException("Limit of trials has reached");
        }

        //initializing success_rate
        int noRightAnswers =0;

        List<String> choosen_options = trialInfo.getChoosenOptions();

        //check rightAnswer number
        Quiz spQuiz = quizRepo.getById(trialInfo.getQuizId());
        List<Question> questions = spQuiz.getQuestions(); //new ArrayList<Question>();

//        questions.add(0, new Question(0,"AB"));
//        questions.add(1, new Question(1,"BC"));
//        questions.add(2, new Question(1,"A"));

        for (int i = 0; i < questions.size(); i++) {
            if(choosen_options.get(i).equals(questions.get(i).getCorrect_option()))
                noRightAnswers++;
        }

        double newSuccessRate = noRightAnswers/ (double) questions.size();

        // add to DB
        // convert ChoosenOptions to String
        String newChoosenOptions = "";
        for (int i = 0; i < choosen_options.size()-1; i++) {
            newChoosenOptions = newChoosenOptions + choosen_options.get(i) + ",";
        }
        newChoosenOptions = newChoosenOptions + choosen_options.get(choosen_options.size()-1);

        Trial newTrial = new Trial(trialInfo.getDevId(), trialInfo.getQuizId(), newTrNo, newSuccessRate, newChoosenOptions, false);

        //if attempts are all made
        if(newTrNo == 3)
            newTrial.setPassed(true);
        trialRepo.save(newTrial);

        TrialDto newDto = modelMapper.map(newTrial, TrialDto.class);
        newDto.setChoosenOptions(Arrays.asList(newTrial.getChoosenOptions().split("\\s*,\\s*")));
        return newDto;
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
        TrialDto newDto = modelMapper.map(trial, TrialDto.class);
        newDto.setChoosenOptions(Arrays.asList(trial.getChoosenOptions().split("\\s*,\\s*")));
        return newDto;
    }

    @Override
    public WrapDto<List<TrialDto>> getTrials(Long quizId, Long devId) {
        List<Trial> trials = trialRepo.getByQuizIdAndDevId(quizId,devId);
        List<TrialDto> dtoList = new ArrayList<TrialDto>();

        for (Trial trial: trials) {
            TrialDto newDto = modelMapper.map(trial, TrialDto.class);
            newDto.setChoosenOptions(Arrays.asList(trial.getChoosenOptions().split("\\s*,\\s*")));
            TrialDto trialDto = newDto;
            dtoList.add(trialDto);
        }
        return new WrapDto<>(dtoList);
    }

    @Override
    public WrapDto<List<Pair<Quiz, List<TrialDto>>>> getAllDevTrials(Long devId) {
        // constructing buckets for each trial with quizId
        List<Trial> rawTrials = trialRepo.getByDevId(devId);
        List<List<Trial>> trials = new ArrayList<List<Trial>>();

        for (Trial spTrial: rawTrials) {
            boolean found = false;
            for ( List<Trial> listTrial: trials) {
                if(listTrial.get(0) != null & listTrial.get(0).getQuizId() == spTrial.getQuizId()) {
                    listTrial.add(spTrial);
                    found = true;
                }
            }
            if(!found){
                List<Trial> temp = new ArrayList<Trial>();
                temp.add(spTrial);
                trials.add(temp);

            }
        }

        List<Pair<Quiz,List<TrialDto>>> dtoList = new ArrayList<>();

        for (List<Trial> devTrial: trials) {

            List<TrialDto> devDto = new ArrayList<>();

            for (Trial trial: devTrial) {

                TrialDto newDto = modelMapper.map(trial, TrialDto.class);
                newDto.setChoosenOptions(Arrays.asList(trial.getChoosenOptions().split("\\s*,\\s*")));
                TrialDto trialDto = newDto;

                devDto.add(trialDto);
            }
            Pair<Quiz, List<TrialDto>> quizTrials = Pair.of(quizRepo.getById(devDto.get(0).getQuizId()), devDto);
            dtoList.add(quizTrials);
        }

        return new WrapDto<List<Pair<Quiz, List<TrialDto>>>>(dtoList);
    }

    @Override
    public WrapDto<List<TrialDto>> getQuizTrials(Long quizId) {
        List<Trial> trials = trialRepo.getByQuizIdOrderBySuccessRateAsc(quizId);
        List<TrialDto> dtoList = new ArrayList<TrialDto>();

        for (Trial trial: trials) {
            TrialDto newDto = modelMapper.map(trial, TrialDto.class);
            newDto.setChoosenOptions(Arrays.asList(trial.getChoosenOptions().split("\\s*,\\s*")));
            TrialDto trialDto = newDto;

            dtoList.add(trialDto);
        }
        WrapDto wrap =  new WrapDto<>(dtoList);
        return wrap;
    }
}
