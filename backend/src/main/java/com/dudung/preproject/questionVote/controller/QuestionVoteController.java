package com.dudung.preproject.questionVote.controller;

import com.dudung.preproject.member.service.MemberService;
import com.dudung.preproject.question.service.QuestionService;
import com.dudung.preproject.questionVote.dto.QuestionVoteDto;
import com.dudung.preproject.questionVote.mapper.QuestionVoteMapper;
import com.dudung.preproject.questionVote.service.QuestionVoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/questionvote")
@RequiredArgsConstructor
public class QuestionVoteController {

    private final QuestionVoteService questionVoteService;
    private final MemberService memberService;
    private final QuestionService questionService;
    private final QuestionVoteMapper mapper;

    @PostMapping
    public ResponseEntity postQuestionVoteToVoteDown(@RequestBody QuestionVoteDto.QuestionVotePost requestBody) {

        if (requestBody.isVote()) {
            questionVoteService.questionVoteUp(memberService.findMember(requestBody.getMemberId()), questionService.findVerifiedQuestion(requestBody.getQuestionId()));
        } else {
            questionVoteService.questionVoteDown(memberService.findMember(requestBody.getMemberId()), questionService.findVerifiedQuestion(requestBody.getQuestionId()));
        }

        return new ResponseEntity<>(mapper.questionToQuestionVoteResponse(questionService.findVerifiedQuestion(requestBody.getQuestionId())), HttpStatus.OK);

    }
}
