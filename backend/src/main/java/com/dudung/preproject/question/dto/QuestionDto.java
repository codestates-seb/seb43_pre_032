package com.dudung.preproject.question.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class QuestionDto {
    @Getter
    public static class Post {
        private long memberId;
        private String questionTitle;
        private String questionContent;
        private LocalDateTime createdAt = LocalDateTime.now();
    }
    @Getter
    @Setter
    public static class Patch {
        private long memberId;
        private long questionId;
        private String questionTitle;
        private String questionContent;
        private LocalDateTime modifiedAt = LocalDateTime.now();
    }

    @Getter
    @Builder
    public static class Response {
        private String questionTitle;
        private String questionContent;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private int questionVoteSum;
        private String member;
    }

    @Getter
    @Builder
    public static class ResponseForList {
        private String questionTitle;
        private int viewCount;
        private int questionVoteSum;
        private LocalDateTime createdAt;
        private String memberName;
        private int answerCount;
    }
}
