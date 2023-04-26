package com.dudung.preproject.tag.dto;

import com.dudung.preproject.question.dto.QuestionDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class TagDto {
    @Getter
    @Builder
    public static class Response {
        private long tagId;
        private String tagName;
        private String tagDescription;
        private List<QuestionDto.ResponseForList> questions;
    }
    @Getter
    @Builder
    public static class ResponseForList {
        private long tagId;
        private String tagName;
        private String tagDescription;
        private int questions;
    }

    @Getter
    @Builder
    public static class Name {
        private long tagId;
        private String tagName;
    }
}
