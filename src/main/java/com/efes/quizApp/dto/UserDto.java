package com.efes.quizApp.dto;

import com.efes.quizApp.entity.JobSituation;
import com.efes.quizApp.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;


    private String username;


    private String email;


    private String password;


    private String nameSurname;


    private String nationality;


    private String description;


    private Role role;


    private String contactInfo;


    private JobSituation situation;


    private String image;


    private String bannedBy;


    private String cv;


}
