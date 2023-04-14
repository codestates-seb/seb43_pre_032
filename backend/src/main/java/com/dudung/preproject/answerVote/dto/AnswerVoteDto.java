package com.dudung.preproject.answerVote.dto;

import lombok.Getter;

public class AnswerVoteDto {

    @Getter
    public static class Post {
        /*
        게시글 답변 ID
        게시글 답변 추천 & 비추천
        추천 받은 수
        비추천 받은 수
         */
        private Long answerVoteId;
        private Long memberId;
        private int score;

    }

    public class Patch {
        private Long answerVoteId;
        private int score;

    }

    public class Response {
        private int score;

    }
}
