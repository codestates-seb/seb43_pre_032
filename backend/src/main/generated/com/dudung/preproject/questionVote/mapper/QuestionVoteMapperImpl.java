package com.dudung.preproject.questionVote.mapper;

import com.dudung.preproject.questionVote.domain.QuestionVote;
import com.dudung.preproject.questionVote.dto.QuestionVoteDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-24T12:06:33+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.18 (Azul Systems, Inc.)"
)
@Component
public class QuestionVoteMapperImpl implements QuestionVoteMapper {

    @Override
    public QuestionVote questionVotePostDtoToQuestionVote(QuestionVoteDto.QuestionVotePost requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        QuestionVote questionVote = new QuestionVote();

        return questionVote;
    }

    @Override
    public QuestionVote questionVotePatchDtoToQuestionVote(QuestionVoteDto.QuestionVotePatch requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        QuestionVote questionVote = new QuestionVote();

        questionVote.setQuestionVoteId( requestBody.getQuestionVoteId() );

        return questionVote;
    }
}
