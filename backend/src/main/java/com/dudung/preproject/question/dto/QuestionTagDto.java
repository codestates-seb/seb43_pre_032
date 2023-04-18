package com.dudung.preproject.question.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class QuestionTagDto {
    @Getter
    public static class Add {
        private long tagId;
    }
    @Getter
    @Builder
    public static class Response {
        private long tagId;
        private String tagName;
    }
}
