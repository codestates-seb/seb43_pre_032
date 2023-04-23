package com.dudung.preproject.answer.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class AnswerAnswerDto {

    @Getter
    @Setter
    public static class Post {
        private Long memberId;
        private Long answerId;
        private String answerAnswerContent;
        private LocalDateTime createdAt = LocalDateTime.now();
    }

    @Getter
    @Setter
    public static class Patch {

        private Long answerAnswerId;
        private Long memberId;
        private Long answerId;
        private String answerAnswerContent;
        private LocalDateTime modifiedAt = LocalDateTime.now();
    }

    @Getter
    @Builder
    public static class Response {
        private Long answerAnswerId;
        private String answerAnswerContent;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private Long memberId;
        private String memberName;
        private Integer memberReputation;
    }

}
