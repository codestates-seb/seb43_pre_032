package com.dudung.preproject.helper;

import com.dudung.preproject.member.dto.MemberDto;
import com.dudung.preproject.question.dto.QuestionDto;
import com.dudung.preproject.question.dto.QuestionResponseDto;
import org.springframework.http.HttpMethod;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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
    public static class MockQuestion {
        private static Map<HttpMethod, Object> stubRequestBody;
        static {
            QuestionDto.Post post = new QuestionDto.Post();
            post.setMemberId(1L);
            post.setQuestionTitle("두둥탁");
            post.setQuestionContent("두둥두둥");

            QuestionDto.Patch patch = new QuestionDto.Patch();
            patch.setMemberId(1L);
            patch.setQuestionId(1L);
            patch.setQuestionTitle("두둥탁");
            patch.setQuestionContent("두둥두둥");

            stubRequestBody = new HashMap<>();
            stubRequestBody.put(HttpMethod.POST, post);
            stubRequestBody.put(HttpMethod.PATCH, patch);
        }

        public static Object getRequestBody(HttpMethod method) {
            return stubRequestBody.get(method);
        }

        public static QuestionDto.Response getResponseBody() {
            LocalDateTime time = LocalDateTime.now();

            QuestionResponseDto questionResponseDto = QuestionResponseDto.builder()
                    .questionTitle("두둥탁")
                    .questionContent("두둥두둥")
                    .createdAt(time)
                    .modifiedAt(time)
                    .questionVoteSum(0)
                    .viewCount(1)
                    .memberName("두둥탁")
                    .build();

            return new QuestionDto.Response(questionResponseDto, null);
        }
    }
}
