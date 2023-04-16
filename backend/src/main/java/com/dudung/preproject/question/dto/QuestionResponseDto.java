package com.dudung.preproject.question.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class QuestionResponseDto {
    private String questionTitle;
    private String questionContent;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private int questionVoteSum;
    private int viewCount;
    private String memberName;
}
