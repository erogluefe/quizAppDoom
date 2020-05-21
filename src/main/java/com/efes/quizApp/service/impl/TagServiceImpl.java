package com.efes.quizApp.service.impl;

import com.efes.quizApp.entity.Tag;
import com.efes.quizApp.repository.TagRepository;
import com.efes.quizApp.service.TagService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;


    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag save(Tag project) {
        return null;
    }

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }
}
