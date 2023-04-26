package com.dudung.preproject.answerVote.mapper;

import com.dudung.preproject.answer.domain.Answer;
import com.dudung.preproject.answer.dto.AnswerDto;
import com.dudung.preproject.answerVote.domain.AnswerVote;
import com.dudung.preproject.answerVote.dto.AnswerVoteDto;
import com.dudung.preproject.member.domain.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerVoteMapper {
    AnswerVote answerVotePostToAnswerVote(AnswerVoteDto.Post requestBody);

    AnswerVote answerVotePatchAnswerVote(AnswerVoteDto.Patch requestBody);

    default AnswerVoteDto.Response answerVoteToAnswerVoteResponse(Answer answer) {
     return AnswerVoteDto.Response.builder()
             .score(answer.getAnswerVoteSum()).build();
    }



}
