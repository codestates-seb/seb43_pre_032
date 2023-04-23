package com.dudung.preproject.question.service;

import com.dudung.preproject.exception.BusinessLogicException;
import com.dudung.preproject.exception.ExceptionCode;
import com.dudung.preproject.member.domain.Member;
import com.dudung.preproject.member.repository.MemberRepository;
import com.dudung.preproject.member.service.MemberService;
import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.question.domain.QuestionTag;
import com.dudung.preproject.question.repository.QuestionRepository;
import com.dudung.preproject.question.repository.QuestionTagRepository;
import com.dudung.preproject.tag.domain.Tag;
import com.dudung.preproject.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Iterator;
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
    private final QuestionTagRepository questionTagRepository;


    public Question createQuestion(Question question, long authenticationMemeberId) {
        checkVerifiedId(authenticationMemeberId);
        Member member = memberService.findMember(authenticationMemeberId);
        question.setMember(member);

        Question createdQuestion = questionRepository.save(question);
        insertTag(createdQuestion);
        member.addQuestion(createdQuestion);
        memberRepository.save(member);

        return createdQuestion;
    }

    public Question updateQuestion(Question question, long authenticationMemberId) {
        checkVerifiedId(authenticationMemberId);
        Question findedQuestion = findVerifiedQuestion(question.getQuestionId());

        patchPermission(findedQuestion, memberService.findMember(authenticationMemberId));

        Optional.ofNullable(question.getQuestionContent())
                .ifPresent(content -> findedQuestion.setQuestionContent(content));
        Optional.ofNullable(question.getQuestionTags())
                .ifPresent(tagList -> {
                    deleteTag(findedQuestion);
                    insertTag(tagList, findedQuestion);
                });
        findedQuestion.setModifiedAt(question.getModifiedAt());

        return questionRepository.save(findedQuestion);
    }

    public Question findQuestion(long questionId) {
        Question findedQuestion = findVerifiedQuestion(questionId);
        addViewCount(findedQuestion);

        return findedQuestion;
    }
    public Page<Question> findQuestions(int page, int size, String sortBy, String keyword) { // sortBy == 정렬기준 e.g. "questionId"
        if (keyword == null) {
            return questionRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy).descending()));
        }
        return questionRepository.findAllByQuestionTitleContaining(keyword, PageRequest.of(page, size,
                Sort.by(sortBy).descending()));
    }

    public Page<Question> findQuestions(Tag tag, int page, int size, String sortBy) { // sortBy == 정렬기준 e.g. "questionId"
        return questionRepository.findAllByTag(tag, PageRequest.of(page, size, Sort.by(sortBy).descending()));
    }

    public void deleteQuestion(long questionId, long authenticationMemberId) {
        checkVerifiedId(authenticationMemberId);
        Question findQuestion = findVerifiedQuestion(questionId);
        deletePermission(findQuestion, memberService.findMember(authenticationMemberId));
        questionRepository.delete(findQuestion);
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
        List<QuestionTag> list = question.getQuestionTags();
        List<QuestionTag> questionTagList = list.stream().map(questionTag -> questionTagRepository.save(questionTag)).collect(Collectors.toList());
        List<QuestionTag> questionTags = questionTagList.stream().map(questionTag -> {
            Tag realTag = tagRepository.findById(questionTag.getTag().getTagId()).orElseThrow(() -> new BusinessLogicException(ExceptionCode.TAG_NOT_FOUND));
            realTag.addQuestionTag(questionTag);
            return questionTag;
        }).collect(Collectors.toList());

        List<QuestionTag> savedQuestionTag = questionTags.stream().map(questionTag -> questionTagRepository.save(questionTag)).collect(Collectors.toList());

        Iterator<QuestionTag> iterator = savedQuestionTag.iterator();
        while (iterator.hasNext()) {
            QuestionTag questionTag = iterator.next();
            questionTag.addQuestion(question);
        }

        question.setQuestionTags(savedQuestionTag);
    }

    private void insertTag(List<QuestionTag> questionTagList, Question question) {
        List<QuestionTag> savedQuestionTags = questionTagList.stream().map(questionTag -> questionTagRepository.save(questionTag)).collect(Collectors.toList());
        List<QuestionTag> questionsTags = savedQuestionTags.stream().map(questionTag -> {
            Tag realTag = tagRepository.findById(questionTag.getTag().getTagId()).orElseThrow(() -> new BusinessLogicException(ExceptionCode.TAG_NOT_FOUND));
            realTag.addQuestionTag(questionTag);
            return questionTag;
        }).collect(Collectors.toList());

        Iterator<QuestionTag> iterator = questionsTags.iterator();
        while (iterator.hasNext()) {
            QuestionTag questionTag = iterator.next();
            questionTag.setQuestion(question);
        }

        question.setQuestionTags(questionsTags);
    }

    private void deleteTag(Question question) {
        List<QuestionTag> questionTags = question.getQuestionTags();

        questionTags.stream()
                .forEach(questionTag -> questionTagRepository.delete(questionTag));
    }

    private void checkVerifiedId(long authenticationMemeberId) { // 임의로 넣은 값 '-1'
        if (authenticationMemeberId == -1) throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED);
    }

    private void patchPermission(Question question, Member member) {
        if (!question.getMember().getMemberId().equals(member.getMemberId())) {
            throw new BusinessLogicException(ExceptionCode.ONLY_AUTHOR);
        }
    }

    private void deletePermission(Question question, Member member) {
        if (!question.getMember().getMemberId().equals(member.getMemberId()) && !member.getEmail().equals("admin@gmail.com")) {
            throw new BusinessLogicException(ExceptionCode.ONLY_AUTHOR);
        }
    }
}
