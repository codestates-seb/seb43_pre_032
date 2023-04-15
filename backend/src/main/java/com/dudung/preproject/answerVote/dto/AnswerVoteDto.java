package com.dudung.preproject.answerVote.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class AnswerVoteDto {

    @Getter
    public static class Post {
        /*
        게시글 답변 ID
        게시글 답변 추천 & 비추천
        추천 받은 수
        비추천 받은 수
         */
        private Long answerId;
        private Long memberId;
//        private int score;

        private boolean vote;
    }
    @Setter
    @Getter
    public static class Patch {
        private Long answerVoteId;
        private int score;

    }

    @Getter
    @Builder
    public static class Response {
        private int score;

    }
}
