package com.dudung.preproject.answer.controller;

import com.dudung.preproject.answer.domain.Answer;
import com.dudung.preproject.answer.dto.AnswerDto;
import com.dudung.preproject.answer.mapper.AnswerMapper;
import com.dudung.preproject.answer.service.AnswerService;
import com.dudung.preproject.dto.MultiResponseDto;
import com.dudung.preproject.member.service.MemberService;
import com.dudung.preproject.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RequestMapping("/answers")
@RestController
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;
    private final AnswerMapper mapper;

    private final QuestionService questionService;

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity postAnswer(@Valid @RequestBody AnswerDto.Post requestBody) {
        Answer answer = mapper.answerPostToAnswer(requestBody);
        answer.setMember(memberService.findMember(requestBody.getMemberId()));
        answer.setQuestion(questionService.findVerifiedQuestion(requestBody.getQuestionId()));

        Answer createdAnswer = answerService.createAnswer(answer);
        return new ResponseEntity<>(mapper.answerToAnswerResponse(createdAnswer), HttpStatus.CREATED);
    }

    @PatchMapping("{answer-id}")
    public ResponseEntity patchAnswer(@Positive @PathVariable("answer-id") Long answerId,
                                      @Valid @RequestBody AnswerDto.Patch requestBody){
        requestBody.setAnswerId(answerId);

        Answer answer = answerService.updateAnswer(mapper.answerPatchAnswer(requestBody));

        return new ResponseEntity<>(mapper.answerToAnswerResponse(answer), HttpStatus.OK);

    }

    @GetMapping("{answer-id}")
    public ResponseEntity getAnswer(@Positive @PathVariable("answer-id") Long answerId) {

        Answer answer = answerService.findAnswer(answerId);

        return new ResponseEntity<>(mapper.answerToAnswerResponse(answer), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAnswers(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size,
                                     @RequestParam String sortBy) {
        Page<Answer> pageAnswers = answerService.findAnswers(page -1, size, sortBy);
        List<Answer> answers = pageAnswers.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(
                mapper.answerToAnswerResponse(answers), pageAnswers),
                HttpStatus.OK);

    }

    @DeleteMapping("{answer-id}")
    public ResponseEntity deleteAnswer(@PathVariable("answer-id") Long answerId) {
        answerService.deleteAnswer(answerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
