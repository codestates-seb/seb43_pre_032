package com.dudung.preproject.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class MemberDto {
    @Getter
    @Setter
    public static class Post {
        private String email;
        private String password;
        private String name;
    }
    @Getter
    @Setter
    public static class Patch {
        private long memberId;
        private String email;
        private String password;
        private String name;
    }
    @Getter
    @Builder
    public static class Response {
        private Long memberId;
        private String name;
    }

    @Getter
    @Builder
    public  static class ResponseForList {
        private Long memberId;
        private String name;
    }
}
