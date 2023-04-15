package com.dudung.preproject.tag.dto;

import lombok.Builder;
import lombok.Getter;

public class TagDto {
    @Getter
    @Builder
    public static class ResponseForList {
        private String tagName;
        private String tagDescription;
    }
}
