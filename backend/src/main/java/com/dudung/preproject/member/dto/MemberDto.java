package com.dudung.preproject.member.dto;

import com.dudung.preproject.answer.domain.Answer;
import com.dudung.preproject.member.domain.Member;
import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.question.dto.QuestionDto;
import com.dudung.preproject.question.dto.QuestionResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class MemberDto {
    @Getter
    @Setter
    public static class Post {
        private String email;
        private String password;
        private String name;
    }
    @Getter
    @Setter
    public static class Patch {
        private long memberId;
        private String email;
        private String password;
        private String name;
    }
    @Getter
    @Builder
    public static class Response {
        private Long memberId;
        private String name;
    }

    @Getter
    @Builder
    public  static class ResponseForList {
        private Long memberId;
        private String name;
    }

    @Getter
    @Builder
    public static class ResponseMyPage {
        private Long memberId;
        private String name;
        private String email;
        private LocalDateTime createAt;
        private int questionCount;
        private int answerCount;
        private List<QuestionResponseDto.QuestionMemberResponseForList> questionTitle;
        private String answerContent;
    }
}
