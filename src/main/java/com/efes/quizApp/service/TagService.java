package com.efes.quizApp.service;

import com.efes.quizApp.entity.Tag;

import java.util.List;

public interface TagService {

    Tag save(Tag project);

    List<Tag> getAllTags();

}
