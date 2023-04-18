package com.dudung.preproject.dto;

import com.dudung.preproject.tag.dto.TagDto;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class DataListResponseDto<T> {
    private T data;
    private PageInfo pageInfo;
    public DataListResponseDto(T data, Page page) {
        this.data = data;
        this.pageInfo = new PageInfo(page.getNumber() + 1,
                page.getSize(), page.getTotalElements(), page.getTotalPages());
    }
}
