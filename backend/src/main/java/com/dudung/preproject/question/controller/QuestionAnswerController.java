package com.dudung.preproject.question.controller;

import com.dudung.preproject.auth.interceptor.JwtParseInterceptor;
import com.dudung.preproject.question.dto.QuestionAnswerDto;
import com.dudung.preproject.question.mapper.QuestionAnswerMapper;
import com.dudung.preproject.question.service.QuestionAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/questionanswers")
@RequiredArgsConstructor
public class QuestionAnswerController {
    private final QuestionAnswerService questionAnswerService;
    private final QuestionAnswerMapper mapper;
    @PatchMapping("/{questionanswer-id}")
    public ResponseEntity patchQuestionAnswer(@PathVariable("questionanswer-id") @Positive long questionAnswerId,
                                              @RequestBody QuestionAnswerDto.Patch requestBody) {
        long authenticationMemeberId = JwtParseInterceptor.getAuthenticatedMemberId();
        requestBody.setQuestionAnswerId(questionAnswerId);

        questionAnswerService.updateQuestionAnswer(mapper.questionAnswerPatchToQuestionAnswer(requestBody), authenticationMemeberId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{questionanswer-id}")
    public ResponseEntity deleteQuestionAnswer(@PathVariable("questionanswer-id") @Positive long questionAnswerId) {
        long authenticationMemeberId = JwtParseInterceptor.getAuthenticatedMemberId();

        questionAnswerService.deleteQuestionAnswer(questionAnswerId, authenticationMemeberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
