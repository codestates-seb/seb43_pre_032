package com.dudung.preproject.member.dto;

import lombok.Getter;

public class MemberDto {
    @Getter
    public static class Post {
        private String email;
        private String password;
        private String name;
    }
    @Getter
    public static class Patch {
        private String email;
        private String password;
        private String name;
    }
    @Getter
    public static class Response {
        private String name;
    }
}
