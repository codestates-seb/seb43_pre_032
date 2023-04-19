package com.dudung.preproject.question.controller;

import com.dudung.preproject.answer.domain.Answer;
import com.dudung.preproject.answer.mapper.AnswerMapper;
import com.dudung.preproject.answer.service.AnswerService;
import com.dudung.preproject.dto.DataListResponseDto;
import com.dudung.preproject.dto.MultiResponseDto;
import com.dudung.preproject.member.domain.Member;
import com.dudung.preproject.member.service.MemberService;
import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.question.dto.QuestionDto;
import com.dudung.preproject.question.mapper.QuestionMapper;
import com.dudung.preproject.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final static String QUESTION_DEFAULT_URL = "/questions";
    private final QuestionService questionService;
    private final QuestionMapper questionMapper;
    private final AnswerService answerService;

    @PostMapping
    public ResponseEntity postQuestion(@RequestBody QuestionDto.Post requestBody) {
        Question question = questionService.createQuestion(questionMapper.questionPostToQuestion(requestBody));
        URI location = UriComponentsBuilder
                .newInstance()
                .path(QUESTION_DEFAULT_URL + "{question-id}")
                .buildAndExpand(question.getQuestionId())
                .toUri();

        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(questionMapper.questionToQuestionResponse(question, question.getAnswers()));
    }

    @PatchMapping("/{question-id}")
    public ResponseEntity patchQuestion(@PathVariable("question-id") long questionId,
                                        @RequestBody QuestionDto.Patch requestBody) {
        requestBody.setQuestionId(questionId);
        Question question = questionService.updateQuestion(questionMapper.questionPatchToQuestion(requestBody));

        return new ResponseEntity<>(questionMapper.questionToQuestionResponse(question, question.getAnswers()), HttpStatus.OK);
    }

    @GetMapping("/{question-id}")
    public ResponseEntity getQuestion(@PathVariable("question-id") @Positive long qustionId, @Positive @RequestParam int page, @Positive @RequestParam int size, @RequestParam String sortBy) {
        Question question = questionService.findQuestion(qustionId);
        Page<Answer> pageAnswers = answerService.findAnswers(page - 1, size, sortBy);
        List<Answer> answers = pageAnswers.getContent();

        return new ResponseEntity<>(new DataListResponseDto(questionMapper.questionToQuestionResponse(question, answers), pageAnswers), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getQuestions(@Positive @RequestParam int page, @Positive @RequestParam int size, @RequestParam String sortBy, @RequestParam(required = false) String keyword) {
        Page<Question> pageQuestions = questionService.findQuestions(page - 1, size, sortBy, keyword);
        List<Question> questions = pageQuestions.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(questionMapper.questionsToQuestionsResponse(questions), pageQuestions), HttpStatus.OK);
    }

    @DeleteMapping("/{question-id}")
    public ResponseEntity deleteQuestion(@PathVariable("question-id") @Positive long questionId) {
        questionService.deleteQuestion(questionId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
