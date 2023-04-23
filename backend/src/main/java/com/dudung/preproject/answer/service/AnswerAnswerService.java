package com.dudung.preproject.answer.service;

import com.dudung.preproject.answer.domain.AnswerAnswer;
import com.dudung.preproject.answer.repository.AnswerAnswerRepository;
import com.dudung.preproject.exception.BusinessLogicException;
import com.dudung.preproject.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerAnswerService {

    private final AnswerAnswerRepository answerAnswerRepository;

    public AnswerAnswer createAnswerAnswer(AnswerAnswer answerAnswer) {
        return answerAnswerRepository.save(answerAnswer);
    }

    public AnswerAnswer updateAnswerAnswer (AnswerAnswer answerAnswer) {
        AnswerAnswer findAnswerAnswer = findVerifiedAnswerAnswer(answerAnswer.getAnswerAnswerId());

        Optional.ofNullable(answerAnswer.getAnswer())
                .ifPresent(answer -> findAnswerAnswer.setAnswer(answer));
        Optional.ofNullable(answerAnswer.getAnswerAnswerModifiedAt())
                .ifPresent(localDateTime -> findAnswerAnswer.setAnswerAnswerModifiedAt(LocalDateTime.now()));

        return answerAnswerRepository.save(findAnswerAnswer);
    }

    public AnswerAnswer findAnswerAnswer(Long answeranswerId) {
        return findVerifiedAnswerAnswer(answeranswerId);
    }

    private AnswerAnswer findVerifiedAnswerAnswer(Long answeranswerId) {
        Optional<AnswerAnswer> optionalAnswerAnswer = answerAnswerRepository.findById(answeranswerId);
        AnswerAnswer findAnswerAnswer = optionalAnswerAnswer.orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));

        return findAnswerAnswer;
    }

    public void deleteAnswerAnswer(Long answeranswerId) {
        AnswerAnswer findAnswer = findVerifiedAnswerAnswer(answeranswerId);
        answerAnswerRepository.delete(findAnswer);
    }


}
