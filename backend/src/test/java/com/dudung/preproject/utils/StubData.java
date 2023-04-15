package com.dudung.preproject.utils;

import com.dudung.preproject.member.dto.MemberDto;

public class StubData {
    public static String getPostMemberContent() {
        return "{\n" +
                "\"email\": \"dudung@gamil.com\",\n" +
                "\"password\": \"1111\",\n" +
                "\"name\": \"두둥탁\"\n" +
                "}";
    }

    public static String getPatchMemberContent() {
        return "{\n" +
                "\"memberId\": \"1\",\n" +
                "\"email\": \"dudung@gamil.com\",\n" +
                "\"password\": \"1111\",\n" +
                "\"name\": \"두둥탁\"\n" +
                "}";
    }

    public static MemberDto.Response getMemberResponse() {
        return MemberDto.Response.builder()
                .name("두둥탁")
                .build();
    }
}
