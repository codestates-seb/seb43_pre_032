package com.dudung.preproject.question.dto;

import java.time.LocalDateTime;

public class QuestionAnswerDto {
    public static class Post {
        private Long QuestionId;
        private Long memberId;
        private String QuestionAnswerContent;
        private LocalDateTime createdAt;
    }

    public static class Patch {
        private Long QuestionId;
        private Long memberId;
        private String QuestionAnswerContent;
        private LocalDateTime modifiedAt;
    }
}
