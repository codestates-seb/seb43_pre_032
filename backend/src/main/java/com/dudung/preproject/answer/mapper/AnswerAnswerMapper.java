package com.dudung.preproject.answer.mapper;

import com.dudung.preproject.answer.domain.Answer;
import com.dudung.preproject.answer.domain.AnswerAnswer;
import com.dudung.preproject.answer.dto.AnswerAnswerDto;
import com.dudung.preproject.member.domain.Member;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface AnswerAnswerMapper {
    AnswerAnswer answerAnswerPostAnswerANswer(AnswerAnswerDto.Post requestBody);

    AnswerAnswer answerAnswerPatchAnswerAnswer(AnswerAnswerDto.Patch requestBody);

    default AnswerAnswer answerAnswerPostToAnswerAnswer(AnswerAnswerDto.Post requestBody){
        AnswerAnswer answerAnswer = new AnswerAnswer();
        Answer answer = new Answer();
        Member member = new Member();
        answer.setAnswerId(requestBody.getAnswerId());
        member.setMemberId(requestBody.getMemberId());
        answerAnswer.setAnswer(answer);
        answerAnswer.setMember(member);
        answerAnswer.setAnswerAnswerContent(requestBody.getAnswerAnswerContent());
        answerAnswer.setAnswerAnswerCreateAt(LocalDateTime.now());

        return answerAnswer;
    }

    default AnswerAnswer answerAnswerPatchToAnswerAnswer(AnswerAnswerDto.Patch requestBody){
        AnswerAnswer answerAnswer = new AnswerAnswer();
        Answer answer = new Answer();
        Member member = new Member();
        answer.setAnswerId(requestBody.getAnswerId());
        member.setMemberId(requestBody.getMemberId());
        answerAnswer.setAnswerAnswerId(requestBody.getAnswerAnswerId());
        answerAnswer.setAnswer(answer);
        answerAnswer.setMember(member);
        answerAnswer.setAnswerAnswerContent(requestBody.getAnswerAnswerContent());
        answerAnswer.setAnswerAnswerModifiedAt(LocalDateTime.now());
        return answerAnswer;
    }

}
