package com.dudung.preproject.helper;

import com.dudung.preproject.member.controller.MemberControllerTest;
import org.springframework.restdocs.request.ParameterDescriptor;

import java.util.Arrays;
import java.util.List;

import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

public interface MemberControllerHelper extends ControllerHelper {
    String MEMBER_DEFAULT_URL = "/members";
    String MEMBER_RESOURCE_ID = "/{member-id}";
    String MEMBER_RESOURCE_URI = MEMBER_DEFAULT_URL + MEMBER_RESOURCE_ID;

    String MYPAGE_DEFAULT_URL = "/members/mypage";
    String MYPAGE_RESOURCE_ID = "/{member-id}";
    String MYPAGE_RESOURCE_URI = MYPAGE_DEFAULT_URL + MYPAGE_RESOURCE_ID;

    default List<ParameterDescriptor> getMemberRequestPathParameterDescriptor() {
        return Arrays.asList(parameterWithName("member-id").description("회원 식별 번호"));
    }
}