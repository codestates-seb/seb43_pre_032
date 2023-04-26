package com.dudung.preproject.helper;

import com.dudung.preproject.member.controller.MemberControllerTest;

public interface MemberControllerHelper extends ControllerHelper {
    String MEMBER_DEFAULT_URL = "/members";
    String MEMBER_RESOURCE_ID = "/{member-id}";
    String MEMBER_RESOURCE_URI = MEMBER_DEFAULT_URL + MEMBER_RESOURCE_ID;

}
