package com.dudung.preproject.tag.service;

import com.dudung.preproject.exception.BusinessLogicException;
import com.dudung.preproject.exception.ExceptionCode;
import com.dudung.preproject.tag.domain.Tag;
import com.dudung.preproject.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public Tag findTag(long tagId) {
        return findVerifiedTag(tagId);
    }

    public Page<Tag> findTags(int page, int size, String sortBy) {
        return tagRepository.findAll(PageRequest.of(page, size,
                Sort.by(sortBy).ascending()));
    }

    private Tag findVerifiedTag(long tagId) {
        Optional<Tag> optionalTag = tagRepository.findById(tagId);
        Tag findedTag = optionalTag.orElseThrow(() -> new BusinessLogicException(ExceptionCode.TAG_NOT_FOUND));

        return findedTag;
    }
}
