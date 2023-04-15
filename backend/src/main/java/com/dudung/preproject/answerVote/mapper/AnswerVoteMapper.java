package com.dudung.preproject.answerVote.mapper;

import com.dudung.preproject.answer.dto.AnswerDto;
import com.dudung.preproject.answerVote.domain.AnswerVote;
import com.dudung.preproject.answerVote.dto.AnswerVoteDto;
import com.dudung.preproject.member.domain.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerVoteMapper {
    AnswerVote answerVotePostToAnswerVote(AnswerVoteDto.Post requestBody);

    AnswerVote answerVotePatchAnswerVote(AnswerVoteDto.Patch requestBody);

    AnswerVoteDto.Response answerVoteToAnswerVoteResponse(AnswerVote answerVote);



}
