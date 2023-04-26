package com.dudung.preproject.member.controller;

import com.dudung.preproject.auth.interceptor.JwtParseInterceptor;
import com.dudung.preproject.dto.MultiResponseDto;
import com.dudung.preproject.member.domain.Member;
import com.dudung.preproject.member.dto.MemberDto;
import com.dudung.preproject.member.mapper.MemberMapper;
import com.dudung.preproject.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;
import org.apache.commons.io.IOUtils;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final static String MEMBER_DEFAULT_URL = "/members";

    private final static String IMAGE_DEFAULT_URL = "/home/ubuntu/seb43_pre_032/backend/src/main/resources/static/image";
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
        long authenticationMemberId = JwtParseInterceptor.getAuthenticatedMemberId();

        memberService.deleteMember(memberId, authenticationMemberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/mypage/{member-id}")
    public ResponseEntity getMemberMyPage(@Positive @PathVariable("member-id") long memberId) {

        return new ResponseEntity<>(mapper.memberToMyPage
                (memberService.findMember(memberId)), HttpStatus.OK);
    }
    @PostMapping(path = "/upload/{member-id}")
    public ResponseEntity postImageUpload(@PathVariable("member-id") long memberId,
                                          @RequestPart(required = false) MultipartFile file){
        long authenticationMemberId = JwtParseInterceptor.getAuthenticatedMemberId();

        memberService.uploading(file, memberId, authenticationMemberId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/mypage/{member-id}")
    public ResponseEntity patchMyPage(@Positive @PathVariable("member-id") long memberId,
                                      @Valid @RequestBody MemberDto.MyPagePatch requestBody) {
        long authenticationMemberId = JwtParseInterceptor.getAuthenticatedMemberId();

        requestBody.setMemberId(memberId);
        memberService.updateMyPage(mapper.responserMypagePatchToMember(requestBody), authenticationMemberId);
        return new ResponseEntity<>(mapper.memberToMyPage(memberService.findMember(memberId)), HttpStatus.OK);
    }

    @GetMapping("/image/{member-id}")
    public ResponseEntity<byte[]> getProfileImage(@PathVariable("member-id") long memberId) throws IOException {
        String dir = Long.toString(memberId);
        String fileExtension = ".png";
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(IMAGE_DEFAULT_URL + "/" + dir + "/" + dir + ".jpeg");
        } catch (Exception e) {
            inputStream = new FileInputStream(IMAGE_DEFAULT_URL + "/" + dir + "/" + dir + ".png");
        } finally {
            byte[] imageByteArray = IOUtils.toByteArray(inputStream);
            inputStream.close();

            HttpHeaders httpHeaders = new HttpHeaders();
            if (".png".equals(fileExtension)) {
                httpHeaders.setContentType(MediaType.IMAGE_PNG);
            } else if (".jpeg".equals(fileExtension)) {
                httpHeaders.setContentType(MediaType.IMAGE_JPEG);
            } return new ResponseEntity<>(imageByteArray, httpHeaders, HttpStatus.OK);
        }
    }
}
