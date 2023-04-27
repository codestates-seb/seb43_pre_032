package com.dudung.preproject.answer.controller;

import com.dudung.preproject.answer.domain.AnswerAnswer;
import com.dudung.preproject.answer.dto.AnswerAnswerDto;
import com.dudung.preproject.answer.mapper.AnswerAnswerMapper;
import com.dudung.preproject.answer.service.AnswerAnswerService;
import com.dudung.preproject.answer.service.AnswerService;
import com.dudung.preproject.auth.interceptor.JwtParseInterceptor;
import com.dudung.preproject.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;

@RequestMapping("/answeranswers")
@RequiredArgsConstructor
@RestController
public class AnswerAnswerController {

    private final AnswerAnswerService answerAnswerService;
    private final AnswerAnswerMapper mapper;

    private final MemberService memberService;
    private final AnswerService answerService;

    private final static String ANSWER_ANSWER_DEFAULT_URL = "/answeranswers";

    @PostMapping
    public ResponseEntity postAnswerAnswer(@Valid @RequestBody AnswerAnswerDto.Post requestBody) {
        long authenticationMemberId = JwtParseInterceptor.getAuthenticatedMemberId();
        AnswerAnswer answerAnswer = mapper.answerAnswerPostToAnswerAnswer(requestBody);

        AnswerAnswer createdAnswer = answerAnswerService.createAnswerAnswer(answerAnswer, authenticationMemberId);
        URI location = UriComponentsBuilder
                .newInstance()
                .path(ANSWER_ANSWER_DEFAULT_URL + "{answeranswer-id}")
                .buildAndExpand(createdAnswer.getAnswerAnswerId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{answeranswer-id}")
    public ResponseEntity patchAnswerAnswer(@Positive @PathVariable("answeranswer-id") Long answeranswerId,
                                            @Valid @RequestBody AnswerAnswerDto.Patch requestBody) {
        long authenticationMemberId = JwtParseInterceptor.getAuthenticatedMemberId();
        requestBody.setAnswerId(answeranswerId);

        answerAnswerService.updateAnswerAnswer(mapper.answerAnswerPatchAnswerAnswer(requestBody), authenticationMemberId);

        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{answeranswer-id}")
    public ResponseEntity deleteAnswerAnswer(@PathVariable("answeranswer-id") @Positive Long answeranswerId) {
        long authenticationMemberId = JwtParseInterceptor.getAuthenticatedMemberId();
        answerAnswerService.deleteAnswerAnswer(answeranswerId, authenticationMemberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}