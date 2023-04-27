package com.dudung.preproject.questionVote.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class QuestionVoteDto {
    @Getter
    @Setter
    public static class QuestionVotePost {
        private long questionId;
        private long memberId;
        private boolean vote;
    }

    @Getter
    @Setter
    public static class QuestionVotePatch {
        private long questionVoteId;
        private long questionId;
        private long memberId;
        private int score;
    }

    @Getter
    @Builder
    public static class QuestionVoteResponse {
        private int score;
    }
}
