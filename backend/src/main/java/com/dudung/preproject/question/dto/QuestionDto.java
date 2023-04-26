package com.dudung.preproject.question.dto;

import com.dudung.preproject.answer.dto.AnswerDto;
import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.tag.dto.TagDto;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

public class QuestionDto {
    @Getter
    @Setter
    public static class Post {
        @NotBlank (message = "제목을 입력하세요.")
        @Size (min = 1, max = 500, message = "질문제목은 500자를 넘을 수 없습니다.")
        private String questionTitle;
        @NotBlank (message = "내용을 입력하세요.")
        private String questionContent;
        private List<QuestionTagDto.Add> tagName;
        private LocalDateTime createdAt = LocalDateTime.now();
    }
    @Getter
    @Setter
    public static class Patch {
        private long questionId;
        @NotBlank (message = "제목을 입력하세요.")
        @Size (min = 1, max = 500, message = "질문제목은 500자를 넘을 수 없습니다.")
        private String questionTitle;
        @NotBlank (message = "내용을 입력하세요.")
        private String questionContent;
        private List<QuestionTagDto.Add> tagName;
        private LocalDateTime modifiedAt = LocalDateTime.now();
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private QuestionResponseDto question;
        private List<AnswerDto.ResponseForList> answer;
    }

    @Getter
    @Builder
    public static class ResponseForList {
        private long questionId;
        private String questionTitle;
        private String questionContent;
        private int viewCount;
        private int questionVoteSum;
        private LocalDateTime createdAt;
        private List<QuestionTagDto.Response> tagName;
        private Question.LastStatus lastStatus;
        private LocalDateTime lastStatusTime;
        private long memberId;
        private String memberJpegUrl;
        private String memberPngUrl;
        private String memberName;
        private int memberReputation;
        private int answerCount;
    }
}
