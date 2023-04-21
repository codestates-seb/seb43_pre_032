package com.dudung.preproject.member.controller;

import com.dudung.preproject.dto.MultiResponseDto;
import com.dudung.preproject.member.domain.Member;
import com.dudung.preproject.member.dto.MemberDto;
import com.dudung.preproject.member.mapper.MemberMapper;
import com.dudung.preproject.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import java.io.File;
import java.io.IOException;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final static String MEMBER_DEFAULT_URL = "/members";
    private final MemberService memberService;
    private final MemberMapper mapper;

    @PostMapping
    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post requestBody) {
        Member member = mapper.memeberPostToMember(requestBody);

        Member createdMember = memberService.createMember(member);
        URI location = UriComponentsBuilder
                .newInstance()
                .path(MEMBER_DEFAULT_URL + "{member-id}")
                .buildAndExpand(createdMember.getMemberId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping(path = "/{member-id}/upload")
    public ResponseEntity postImageUpload(@PathVariable("member-id") long memberId,
                                          @RequestPart(required = false) MultipartFile file){
        String dir = Long.toString(memberId);
        memberService.uploading(file, dir);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity patchMember(@Positive @PathVariable("member-id") long memberId,
                                      @Valid @RequestBody MemberDto.Patch requestBody) {
        requestBody.setMemberId(memberId);

        Member member = memberService.updateMember(mapper.memberPatchToMember(requestBody));

        return new ResponseEntity<>(mapper.memberToMemberResponse(member), HttpStatus.OK);
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@Positive @PathVariable("member-id") long memberId) {

        Member member = memberService.findMember(memberId);

        return new ResponseEntity<>(mapper.memberToMemberResponse(member), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMembers(@Positive @RequestParam int page, @RequestParam int size) {

        Page<Member> pageMembers = memberService.findMembers(page -1, size);
        List<Member> members = pageMembers.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.membersToMemberResponses(members), pageMembers),
                HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@Positive @PathVariable("member-id") long memberId) {

        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{member-id}/mypage")
    public ResponseEntity getMemberMyPage(@Positive @PathVariable("member-id") long memberId) {

        return new ResponseEntity<>(mapper.memberToMyPage
                (memberService.findMember(memberId)), HttpStatus.OK);
    }
}
