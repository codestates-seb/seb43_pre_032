package com.dudung.preproject.questionVote.mapper;

import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.questionVote.domain.QuestionVote;
import com.dudung.preproject.questionVote.dto.QuestionVoteDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionVoteMapper {
    QuestionVote questionVotePostDtoToQuestionVote(QuestionVoteDto.QuestionVotePost requestBody);
    QuestionVote questionVotePatchDtoToQuestionVote(QuestionVoteDto.QuestionVotePatch requestBody);
    default QuestionVoteDto.QuestionVoteResponse questionToQuestionVoteResponse(Question question) {
        return QuestionVoteDto.QuestionVoteResponse.builder()
                .score(question.getQuestionVoteSum()).build();
    }
}
