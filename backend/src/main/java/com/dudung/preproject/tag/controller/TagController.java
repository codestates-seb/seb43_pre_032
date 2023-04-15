package com.dudung.preproject.tag.controller;

import com.dudung.preproject.dto.MultiResponseDto;
import com.dudung.preproject.tag.domain.Tag;
import com.dudung.preproject.tag.mapper.TagMapper;
import com.dudung.preproject.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;
    private final TagMapper mapper;

    @GetMapping
    public ResponseEntity getTags(@Positive @RequestParam int page, @Positive @RequestParam int size, @RequestParam String sortBy) {
        Page<Tag> pageTags = tagService.findTags(page - 1, size, sortBy);
        List<Tag> tags = pageTags.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(mapper.tagsToTagsResponse(tags), pageTags), HttpStatus.OK);
    }

}
