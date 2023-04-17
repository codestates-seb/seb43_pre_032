package com.dudung.preproject.dto;

import com.dudung.preproject.tag.dto.TagDto;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class TagQuestionListResponseDto {
    private TagDto.Response data;
    private PageInfo pageInfo;
    public TagQuestionListResponseDto(TagDto.Response data, Page page) {
        this.data = data;
        this.pageInfo = new PageInfo(page.getNumber() + 1,
                page.getSize(), page.getTotalElements(), page.getTotalPages());
    }
}
