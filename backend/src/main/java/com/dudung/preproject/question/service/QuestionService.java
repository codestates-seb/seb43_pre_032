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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
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

        return findedQuestion;
    }
    public Page<Question> findQuestions(int page, String sortBy, String keyword) { // sortBy == 정렬기준 e.g. "questionId"
        if (keyword == null) {
            return questionRepository.findAll(PageRequest.of(page, 10, Sort.by(sortBy).descending()));
        }
        return questionRepository.findAllByQuestionTitleContaining(keyword, PageRequest.of(page, 10,
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

    public void viewCountValidation(Question question, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        boolean isCookie = false;
        // request에 쿠키들이 있을 때
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            // postView 쿠키가 있을 때
            if (cookies[i].getName().equals("questionView")) {
                // cookie 변수에 저장
                cookie = cookies[i];
                // 만약 cookie 값에 현재 게시글 번호가 없을 때
                if (!cookie.getValue().contains("[" + question.getQuestionId() + "]")) {
                    // 해당 게시글 조회수를 증가시키고, 쿠키 값에 해당 게시글 번호를 추가
                    addViewCount(question);
                    cookie.setValue(cookie.getValue() + "[" + question.getQuestionId() + "]");
                }
                isCookie = true;
                break;
            }
        }
        // 만약 postView라는 쿠키가 없으면 처음 접속한 것이므로 새로 생성
        if (!isCookie) {
            addViewCount(question);
            cookie = new Cookie("questionView", "[" + question.getQuestionId() + "]"); // oldCookie에 새 쿠키 생성
        }

        // 쿠키 유지시간을 오늘 하루 자정까지로 설정
        long todayEndSecond = LocalDate.now().atTime(LocalTime.MAX).toEpochSecond(ZoneOffset.UTC);
        long currentSecond = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        cookie.setPath("/"); // 모든 경로에서 접근 가능
        cookie.setMaxAge((int) (todayEndSecond - currentSecond));
        response.addCookie(cookie);
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
