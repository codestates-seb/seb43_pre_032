package com.dudung.preproject.answer.dto;

import com.dudung.preproject.answer.domain.Answer;
import com.dudung.preproject.answerVote.domain.AnswerVote;
import com.dudung.preproject.member.domain.Member;
import com.dudung.preproject.question.domain.Question;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;


public class AnswerDto {
    @Getter
    @Setter
    public static class Post {

        private Long questionId;
        @NotBlank(message = "내용을 입력하세요")
        private String answerContent;

    }

    @Getter
    @Setter
    public static class Patch {


        private Long answerId;

        private Question question;
        @NotBlank(message = "내용을 입력하세요.")
        private String answerContent;

        private LocalDateTime modifiedAt = LocalDateTime.now();
    }
    @Getter
    @Builder
    public static class Response {
        private Long answerId;
        private long questionId;
        private long memberId;
        private String memberName;
        private int memberReputation;
        private String answerContent;
        private LocalDateTime modifiedAt;

    }
    @Getter
    public static class memberToAnswerVote {
        private Member member;
        private Answer answer;

        public memberToAnswerVote(Member member, Answer answer) {
            this.member = member;
            this.answer = answer;
        }
    }
    @Getter
    @Builder
    public static class ResponseForList {

        private Long answerId;
        private String answerContent;
        private int answerVoteSum;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private long memberId;
        private String memberJpegUrl;
        private String memberPngUrl;
        private String memberName;
        private int memberReputation;
        private List<AnswerAnswerDto.Response> answerAnswers;


    }

    @Getter
    @Builder
    public static class AnswerMemberResponseForList {
        private Long questionId;
        private Long answerId;
        private String answerContent;
    }
}
