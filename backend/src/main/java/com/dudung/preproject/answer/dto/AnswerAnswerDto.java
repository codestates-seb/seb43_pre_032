package com.dudung.preproject.answer.dto;

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


}
