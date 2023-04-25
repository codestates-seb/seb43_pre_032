package com.dudung.preproject.answer.mapper;

import com.dudung.preproject.answer.domain.AnswerAnswer;
import com.dudung.preproject.answer.dto.AnswerAnswerDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-24T12:06:33+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.18 (Azul Systems, Inc.)"
)
@Component
public class AnswerAnswerMapperImpl implements AnswerAnswerMapper {

    @Override
    public AnswerAnswer answerAnswerPostAnswerANswer(AnswerAnswerDto.Post requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        AnswerAnswer answerAnswer = new AnswerAnswer();

        answerAnswer.setAnswerAnswerContent( requestBody.getAnswerAnswerContent() );

        return answerAnswer;
    }

    @Override
    public AnswerAnswer answerAnswerPatchAnswerAnswer(AnswerAnswerDto.Patch requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        AnswerAnswer answerAnswer = new AnswerAnswer();

        answerAnswer.setAnswerAnswerId( requestBody.getAnswerAnswerId() );
        answerAnswer.setAnswerAnswerContent( requestBody.getAnswerAnswerContent() );

        return answerAnswer;
    }
}
