package com.dudung.preproject.questionVote.controller;

import com.dudung.preproject.member.service.MemberService;
import com.dudung.preproject.questionVote.dto.QuestionVoteDto;
import com.dudung.preproject.questionVote.mapper.QuestionVoteMapper;
import com.dudung.preproject.questionVote.service.QuestionVoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            questionVoteService.questionvoteup(memberService.findMember(requestBody.getMemberId()), questionService.findQuestion(requestBody.getQuestionId());
        } else {
            questionVoteService.questionvotedown(memberService.findMember(requestBody.getMemberId()), questionService.findQuestion(requestBody.getQuestionId());
        }

        return new ResponseEntity<>(questionService.findQuestion(requestBody.getQuestionId().addQuestionVote(), HttpStatus.OK));

    }
}
