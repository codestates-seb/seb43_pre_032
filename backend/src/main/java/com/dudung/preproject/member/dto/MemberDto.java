package com.dudung.preproject.member.dto;

import com.dudung.preproject.answer.domain.Answer;
import com.dudung.preproject.answer.dto.AnswerDto;
import com.dudung.preproject.member.domain.Member;
import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.question.dto.QuestionDto;
import com.dudung.preproject.question.dto.QuestionResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

public class MemberDto {
    @Getter
    @Setter
    public static class Post {
        @NotBlank(message = "공백이 아니어야 합니다.")
        @Email (message = "이메일 형식을 지켜주세요.")
        private String email;
        @NotBlank (message = "공백이 아니어야 합니다.")
        private String password;
        @NotBlank (message = "공백이 아니어야 합니다.")
        @Size(min = 1, max = 50, message = "이름은 50자리까지 가능합니다.")
        private String name;
    }
    @Getter
    @Setter
    public static class Patch {
        private long memberId;
        @NotBlank (message = "공백이 아니어야 합니다.")
        @Email (message = "이메일 형식을 지켜주세요.")
        private String email;
        @NotBlank (message = "공백이 아니어야 합니다.")
        private String password;
        @NotBlank (message = "공백이 아니어야 합니다.")
        @Size(min = 1, max = 10, message = "이름은 10자리까지 가능합니다.")
        private String name;
    }
    @Getter
    @Builder
    public static class Response {
        private Long memberId;
        private String name;
        private int reputation;
    }

    @Getter
    @Builder
    public  static class ResponseForList {
        private Long memberId;
        private String name;
        private int reputation;
    }

    @Getter
    @Builder
    public static class MyPagePatch {

        private Long memberId;
        private String name;
        private String myPageTitle;
        private String aboutMe;

    }

    @Getter
    @Builder
    public static class ResponseMyPage {
        private Long memberId;
        private String name;
        private String myPageTitle;
        private String aboutMe;
        private LocalDateTime createAt;
        private LocalDateTime modifiedAt;
        private int reputation;
        private int questionCount;
        private int answerCount;
        private List<QuestionResponseDto.QuestionMemberResponseForList> questions;
        private List<AnswerDto.AnswerMemberResponseForList> answers;
    }
}
