package com.dudung.preproject.question.dto;

import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.tag.domain.Tag;
import com.dudung.preproject.tag.dto.TagDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class QuestionResponseDto {
    private long questionId;
    private String questionTitle;
    private String questionContent;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<QuestionTagDto.Response> tagName;
    private int questionVoteSum;
    private int viewCount;
    private long memberId;
    private String memberName;
    private int memberReputation;

    @Getter
    @Builder
    public static class QuestionMemberResponseForList {
        private String questionsTitle;
    }
}
