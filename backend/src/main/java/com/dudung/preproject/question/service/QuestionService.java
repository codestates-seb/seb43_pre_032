package com.dudung.preproject.question.service;

import com.dudung.preproject.exception.BusinessLogicException;
import com.dudung.preproject.exception.ExceptionCode;
import com.dudung.preproject.member.domain.Member;
import com.dudung.preproject.member.repository.MemberRepository;
import com.dudung.preproject.member.service.MemberService;
import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.question.repository.QuestionRepository;
import com.dudung.preproject.tag.domain.Tag;
import com.dudung.preproject.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final TagRepository tagRepository;
    private final MemberService memberService;
    private final MemberRepository memberRepository;


    public Question createQuestion(Question question) {
        Member member = memberService.findVerifiedMember(question.getMember().getMemberId());
        question.setMember(member);
        insertTag(question);

        Question createdQuestion = questionRepository.save(question);
        member.addQuestion(createdQuestion);
        memberRepository.save(member);

        return createdQuestion;
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

    public Page<Question> findQuestions(Tag tag, int page, int size, String sortBy) { // sortBy == 정렬기준 e.g. "questionId"
        return questionRepository.findAllByTagsContains(tag, PageRequest.of(page, size, Sort.by(sortBy).descending()));
    }

    public void deleteQuestion(long questionId) {
        questionRepository.delete(findVerifiedQuestion(questionId));
    }

    public Question findVerifiedQuestion(long questionId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        Question findedQuestion = optionalQuestion.orElseThrow(() -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        return findedQuestion;
    }

    private void addViewCount(Question question) {
        question.setViewCount(question.getViewCount() + 1);

        questionRepository.save(question);
    }

    private void insertTag(Question question) {
        List<Tag> list = question.getTags();
        List<Tag> tags = list.stream().map(tag -> {
            Tag realTag = tagRepository.findById(tag.getTagId()).orElseThrow(() -> new BusinessLogicException(ExceptionCode.TAG_NOT_FOUND));
            realTag.addQuestion(question);
//            tagRepository.save(realTag);
            return realTag;
        }).collect(Collectors.toList());

        question.setTags(tags);
    }
}
