package com.dudung.preproject.answerVote.controller;

import com.dudung.preproject.answer.service.AnswerService;
import com.dudung.preproject.answerVote.dto.AnswerVoteDto;
import com.dudung.preproject.answerVote.mapper.AnswerVoteMapper;
import com.dudung.preproject.answerVote.service.AnswerVoteService;
import com.dudung.preproject.auth.interceptor.JwtParseInterceptor;
import com.dudung.preproject.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/answervote")
public class AnswerVoteController {

    private final AnswerVoteService answerVoteService;
    private final MemberService memberService;
    private final AnswerService answerService;
    private final AnswerVoteMapper mapper;

    @PostMapping
    public ResponseEntity postAnswerVoteToVoteDown(@RequestBody AnswerVoteDto.Post requestBody) {
        long authenticationMemeberId = JwtParseInterceptor.getAuthenticatedMemberId();
        answerVoteService.checkVerifiedId(authenticationMemeberId);
        requestBody.setMemberId(authenticationMemeberId);
        if (requestBody.isVote()) {
            answerVoteService.voteUp(memberService.findMember(requestBody.getMemberId()),
                    answerService.findAnswer(requestBody.getAnswerId()));
        } else {
            answerVoteService.voteDown(memberService.findMember(requestBody.getMemberId()),
                    answerService.findAnswer(requestBody.getAnswerId()));
        }

        return new ResponseEntity<>(mapper.answerVoteToAnswerVoteResponse(answerService.findAnswer(requestBody.getAnswerId())), HttpStatus.OK);
    }
}


