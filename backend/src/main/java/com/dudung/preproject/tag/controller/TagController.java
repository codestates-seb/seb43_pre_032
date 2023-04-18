package com.dudung.preproject.tag.controller;

import com.dudung.preproject.dto.MultiResponseDto;
import com.dudung.preproject.dto.DataListResponseDto;
import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.question.mapper.QuestionMapper;
import com.dudung.preproject.question.service.QuestionService;
import com.dudung.preproject.tag.domain.Tag;
import com.dudung.preproject.tag.mapper.TagMapper;
import com.dudung.preproject.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;
    private final TagMapper tagMapper;
    private final QuestionService questionService;
    private final QuestionMapper questionMapper;

    @GetMapping
    public ResponseEntity getTags(@Positive @RequestParam int page, @Positive @RequestParam int size, @RequestParam String sortBy, @RequestParam(required = false) String keyword) {
        Page<Tag> pageTags = tagService.findTags(page - 1, size, sortBy, keyword);
        List<Tag> tags = pageTags.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(tagMapper.tagsToTagsResponse(tags), pageTags), HttpStatus.OK);
    }

    @GetMapping("/{tag-id}")
    public ResponseEntity getTag(@PathVariable("tag-id") long tagId, @Positive @RequestParam int page, @Positive @RequestParam int size, @RequestParam String sortBy) {
        Tag tag = tagService.findTag(tagId);
        Page<Question> pageQuestions = questionService.findQuestions(tag, page - 1, size, sortBy);
        List<Question> questions = pageQuestions.getContent();

        return new ResponseEntity<>(new DataListResponseDto(tagMapper.tagTotagResponse(tag, questions), pageQuestions), HttpStatus.OK);
    }

}
