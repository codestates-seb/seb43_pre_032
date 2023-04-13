package com.dudung.preproject.answer.dto;

import com.dudung.preproject.member.domain.Member;
import com.dudung.preproject.question.domain.Question;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


public class AnswerDto {
    @Getter
    public static class Post {

        private Long memberId;
        private String answerContent;

    }

    @Getter
    @Setter
    public static class Patch {

//        private Long memberId;

        private Long answerId;

        private Question question;

//        private String answerContent;

//        private LocalDateTime modifiedAt = LocalDateTime.now();
    }
    @Getter
    public static class Response {
        // Ansewr의 Response에 들어갈 내용이 뭐가 있는지 ??
        // 응답 데이터로 뭔가 한다던가 혹은 완료 메세지를 해야하는가?
        private Long answerId;
        private Question question;
        private Member member;
        private String answerContent;
        private LocalDateTime modifiedAt;

    }
}
