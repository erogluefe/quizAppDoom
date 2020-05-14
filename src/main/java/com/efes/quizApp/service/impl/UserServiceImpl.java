package com.efes.quizApp.service.impl;

import com.efes.quizApp.dto.UserDto;
import com.efes.quizApp.entity.User;
import com.efes.quizApp.repository.QuestionRepository;
import com.efes.quizApp.repository.UserRepository;
import com.efes.quizApp.service.UserService;
import com.efes.quizApp.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {

        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public UserDto save(UserDto userDto) {

        User userCheck = userRepository.getByUsername(userDto.getUsername());

        if (userCheck != null)
            throw new IllegalArgumentException("aynı user name var zaten");

        User u = modelMapper.map(userDto, User.class);
        u = userRepository.save(u);
        userDto.setId(u.getId());
        return userDto;


    }


    @Override
    public UserDto getById(Long id) {
        User q = userRepository.getOne(id);
        return modelMapper.map(q, UserDto.class);

    }

    @Override
    public UserDto getByUsername(String username) {
        return null;
    }

    @Override
    public List<UserDto> getByUsernameContains(String username) {
        return null;
    }

    @Override
    public TPage<UserDto> getAllPageable(Pageable pageable) {

        Page<User> data = userRepository.findAll(pageable);
        TPage<UserDto> response = new TPage<UserDto>();
        response.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), UserDto[].class)));

        return response;

    }


    @Override
    public Boolean delete(UserDto userDto) {
        return null;
    }

    public Boolean delete(Long id) {

        userRepository.deleteById(id);
        return true;


    }


    @Override
    public UserDto update(Long id, UserDto userDto) {

        User userDb = userRepository.getOne(id);
        if (userDb == null)
            throw new IllegalArgumentException("bu kayıt db de yok ID: " + id);

        User questionCheck = userRepository.getByUsernameAndIdNot(userDto.getUsername(), id);

        if (questionCheck != null)
            throw new IllegalArgumentException("aynı question kod var zaten");



        userDb.setUsername(userDto.getUsername());
        userDb.setEmail(userDto.getEmail());
        userDb.setPassword(userDto.getPassword());
        userDb.setNameSurname(userDto.getNameSurname());
        userDb.setNationality(userDto.getNationality());
        userDb.setDescription(userDto.getDescription());
        userDb.setRole(userDto.getRole());
        userDb.setContactInfo(userDto.getContactInfo());
        userDb.setSituation(userDto.getSituation());
        userDb.setImage(userDto.getImage());
        userDb.setBannedBy(userDto.getBannedBy());
        userDb.setCv(userDto.getCv());

        userRepository.save(userDb);
        return modelMapper.map(userDb, UserDto.class);


    }

    @Override
    public UserDto getAllUsers() {
        User u = userRepository.findSpecificUser();
        return modelMapper.map(u, UserDto.class);

    }
}
