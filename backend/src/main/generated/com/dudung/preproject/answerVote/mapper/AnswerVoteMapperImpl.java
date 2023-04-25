package com.dudung.preproject.answerVote.mapper;

import com.dudung.preproject.answerVote.domain.AnswerVote;
import com.dudung.preproject.answerVote.dto.AnswerVoteDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-24T12:06:33+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.18 (Azul Systems, Inc.)"
)
@Component
public class AnswerVoteMapperImpl implements AnswerVoteMapper {

    @Override
    public AnswerVote answerVotePostToAnswerVote(AnswerVoteDto.Post requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        AnswerVote answerVote = new AnswerVote();

        return answerVote;
    }

    @Override
    public AnswerVote answerVotePatchAnswerVote(AnswerVoteDto.Patch requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        AnswerVote answerVote = new AnswerVote();

        answerVote.setAnswerVoteId( requestBody.getAnswerVoteId() );

        return answerVote;
    }
}
