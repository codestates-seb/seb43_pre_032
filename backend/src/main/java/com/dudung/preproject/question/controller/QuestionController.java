package com.dudung.preproject.question.controller;

import com.dudung.preproject.answer.domain.Answer;
import com.dudung.preproject.answer.service.AnswerService;
import com.dudung.preproject.auth.interceptor.JwtParseInterceptor;
import com.dudung.preproject.dto.DataListResponseDto;
import com.dudung.preproject.dto.MultiResponseDto;
import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.question.dto.QuestionAnswerDto;
import com.dudung.preproject.question.dto.QuestionDto;
import com.dudung.preproject.question.mapper.QuestionAnswerMapper;
import com.dudung.preproject.question.mapper.QuestionMapper;
import com.dudung.preproject.question.service.QuestionAnswerService;
import com.dudung.preproject.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final static String QUESTION_DEFAULT_URL = "/questions";
    private final QuestionService questionService;
    private final QuestionMapper questionMapper;
    private final AnswerService answerService;
    private final QuestionAnswerService questionAnswerService;
    private final QuestionAnswerMapper questionAnswerMapper;

    @PostMapping
    public ResponseEntity postQuestion(@Valid @RequestBody QuestionDto.Post requestBody) {
        long authenticationMemeberId = JwtParseInterceptor.getAuthenticatedMemberId();

        Question question = questionService.createQuestion(questionMapper.questionPostToQuestion(requestBody), authenticationMemeberId);
        URI location = UriComponentsBuilder
                .newInstance()
                .path(QUESTION_DEFAULT_URL + "{question-id}")
                .buildAndExpand(question.getQuestionId())
                .toUri();

        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(questionMapper.questionToQuestionResponse(question, question.getAnswers()));
    }

    @PatchMapping("/{question-id}")
    public ResponseEntity patchQuestion(@Positive @PathVariable("question-id") long questionId,
                                        @Valid @RequestBody QuestionDto.Patch requestBody) {
        long authenticationMemeberId = JwtParseInterceptor.getAuthenticatedMemberId();

        requestBody.setQuestionId(questionId);
        Question question = questionService.updateQuestion(questionMapper.questionPatchToQuestion(requestBody), authenticationMemeberId);

        return new ResponseEntity<>(questionMapper.questionToQuestionResponse(question, question.getAnswers()), HttpStatus.OK);
    }

    @GetMapping("/{question-id}")
    public ResponseEntity getQuestion(@PathVariable("question-id") @Positive long qustionId, @Positive @RequestParam int page, @RequestParam String sortBy, HttpServletRequest request, HttpServletResponse response) {
        Question question = questionService.findQuestion(qustionId);
        questionService.viewCountValidation(question, request, response);
        Page<Answer> pageAnswers = answerService.findAnswers(page - 1, sortBy);
        List<Answer> answers = pageAnswers.getContent();

        return new ResponseEntity<>(new DataListResponseDto(questionMapper.questionToQuestionResponse(question, answers), pageAnswers), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getQuestions(@Positive @RequestParam int page, @RequestParam String tab, @RequestParam(required = false) String keyword) {
        Page<Question> pageQuestions = questionService.findQuestions(page - 1, tab, keyword);
        List<Question> questions = pageQuestions.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(questionMapper.questionsToQuestionsResponse(questions), pageQuestions), HttpStatus.OK);
    }

    @DeleteMapping("/{question-id}")
    public ResponseEntity deleteQuestion(@PathVariable("question-id") @Positive long questionId) {
        long authenticationMemberId = JwtParseInterceptor.getAuthenticatedMemberId();

        questionService.deleteQuestion(questionId, authenticationMemberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{question-id}")
    public ResponseEntity postQuestionAnswer(@PathVariable("question-id") @Positive long questionId,
                                             @RequestBody QuestionAnswerDto.Post requestBody) {
        long authenticationMemberId = JwtParseInterceptor.getAuthenticatedMemberId();
        requestBody.setQuestionId(questionId);
        questionAnswerService.createQuestionAnswer(questionAnswerMapper.questionAnswerPostToQuestionAnswer(requestBody), authenticationMemberId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
