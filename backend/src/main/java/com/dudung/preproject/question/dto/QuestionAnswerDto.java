package com.dudung.preproject.question.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class QuestionAnswerDto {
    @Getter
    @Setter
    public static class Post {
        private Long questionId;
        private Long memberId;
        private String questionAnswerContent;
    }
    @Getter
    @Setter
    public static class Patch {
        private Long questionAnswerId;
        private Long questionId;
        private Long memberId;
        private String questionAnswerContent;
    }

    @Getter
    @Builder
    public static class Response {
        private Long questionAnswerId;
        private String questionAnswerContent;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private Long memberId;
        private String memberName;
        private Integer memberReputation;

    }
}
