package com.dudung.preproject.question.dto;

import com.dudung.preproject.answer.dto.AnswerDto;
import com.dudung.preproject.tag.dto.TagDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class QuestionDto {
    @Getter
    @Setter
    public static class Post {
        private long memberId;
        private String questionTitle;
        private String questionContent;
        private List<TagDto.Name> tagName;
        private LocalDateTime createdAt = LocalDateTime.now();
    }
    @Getter
    @Setter
    public static class Patch {
        private long memberId;
        private long questionId;
        private String questionTitle;
        private String questionContent;
        private LocalDateTime modifiedAt = LocalDateTime.now();
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private QuestionResponseDto question;
        private AnswerDto.Response answer;
    }

    @Getter
    @Builder
    public static class ResponseForList {
        private String questionTitle;
        private int viewCount;
        private int questionVoteSum;
        private LocalDateTime createdAt;
        private List<TagDto.Name> tagName;
        private String memberName;
        private int answerCount;
    }
}
