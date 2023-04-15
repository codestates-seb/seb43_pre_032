package com.dudung.preproject.answer.dto;

import com.dudung.preproject.member.domain.Member;
import com.dudung.preproject.question.domain.Question;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


public class AnswerDto {
    @Getter
    public static class Post {

        private Long memberId;
        private Long questionId;
        private String answerContent;

    }

    @Getter
    @Setter
    public static class Patch {

        private Long memberId;

        private Long answerId;

        private Question question;

        private String answerContent;

        private LocalDateTime modifiedAt = LocalDateTime.now();
    }
    @Getter
    @Builder
    public static class Response {
        private Long answerId;
        private long questionId;
        private String memberName;
        private String answerContent;
        private LocalDateTime modifiedAt;

    }
}
