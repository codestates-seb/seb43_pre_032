package com.dudung.preproject.question.service;

import com.dudung.preproject.exception.BusinessLogicException;
import com.dudung.preproject.exception.ExceptionCode;
import com.dudung.preproject.member.service.MemberService;
import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final MemberService memberService;


    public Question createQuestion(Question question) {
        memberService.findVerifiedMember(question.getMember().getMemberId());

        return questionRepository.save(question);
    }

    public Question updateQuestion(Question question) {
        Question findedQuestion = findVerifiedQuestion(question.getQuestionId());

        Optional.ofNullable(question.getQuestionContent())
                .ifPresent(content -> findedQuestion.setQuestionContent(content));

        return questionRepository.save(findedQuestion);
    }

    public Question findQuestion(long questionId) {
        Question findedQuestion = findVerifiedQuestion(questionId);
        addViewCount(findedQuestion);

        return findedQuestion;
    }
    public Page<Question> findQuestions(int page, int size, String sortBy) { // sortBy == 정렬기준 e.g. "questionId"
        return questionRepository.findAll(PageRequest.of(page, size,
                Sort.by(sortBy).descending()));
    }

    public void deleteQuestion(long questionId) {
        questionRepository.delete(findVerifiedQuestion(questionId));
    }

    private Question findVerifiedQuestion(long questionId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        Question findedQuestion = optionalQuestion.orElseThrow(() -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        return findedQuestion;
    }

    private void addViewCount(Question question) {
        question.setViewCount(question.getViewCount() + 1);

        questionRepository.save(question);
    }
}
