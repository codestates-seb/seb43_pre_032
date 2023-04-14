package com.dudung.preproject.question.dto;

import java.time.LocalDateTime;

public class QuestionDto {
    public static class Post {
        private long memberId;
        private String questionTitle;
        private String questionContent;
        private LocalDateTime createdAt = LocalDateTime.now();
    }
    public static class Patch {
        private long memberId;
        private long questionId;
        private String questionTitle;
        private String questionContent;
        private LocalDateTime modifiedAt = LocalDateTime.now();
    }

    public static class Response {
        private String questionTitle;
        private String questionContent;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private int questionVoteSum;
        private String memberName;
    }
}
