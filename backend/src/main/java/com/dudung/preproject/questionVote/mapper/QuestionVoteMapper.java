package com.dudung.preproject.questionVote.mapper;

import com.dudung.preproject.questionVote.domain.QuestionVote;
import com.dudung.preproject.questionVote.dto.QuestionVoteDto;

public interface QuestionVoteMapper {
    QuestionVote questionVotePostDtoToQuestionVote(QuestionVoteDto.QuestionVotePost requestBody);
    QuestionVote questionVotePatchDtoToQuestionVote(QuestionVoteDto.QuestionVotePatch requestBody);
    QuestionVoteDto.QuestionVoteResponse questionVoteToQuestionVoteResponse(QuestionVoteDto QuestionVoteDto);
}
