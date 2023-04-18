package com.dudung.preproject.helper;

import com.dudung.preproject.answer.domain.Answer;
import com.dudung.preproject.answer.dto.AnswerDto;
import com.dudung.preproject.member.dto.MemberDto;
import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.question.dto.QuestionDto;
import com.dudung.preproject.question.dto.QuestionResponseDto;
import com.dudung.preproject.question.dto.QuestionTagDto;
import com.dudung.preproject.tag.domain.Tag;
import com.dudung.preproject.tag.dto.TagDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpMethod;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
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
            post.setTagName(getTagName());

            QuestionDto.Patch patch = new QuestionDto.Patch();
            patch.setMemberId(1L);
            patch.setQuestionId(1L);
            patch.setQuestionTitle("두둥탁");
            patch.setQuestionContent("두둥두둥");
            patch.setTagName(getTagName());

            stubRequestBody = new HashMap<>();
            stubRequestBody.put(HttpMethod.POST, post);
            stubRequestBody.put(HttpMethod.PATCH, patch);
        }

        public static List<QuestionTagDto.Add> getTagName() {
            QuestionTagDto.Add questionTag1 = new QuestionTagDto.Add();
            questionTag1.setTagId(1L);
            QuestionTagDto.Add questionTag2 = new QuestionTagDto.Add();
            questionTag2.setTagId(2L);

            return List.of(questionTag1, questionTag2);
        }

        public static Object getRequestBody(HttpMethod method) {
            return stubRequestBody.get(method);
        }

        public static QuestionDto.Response getResponseBody() {
            LocalDateTime time = LocalDateTime.now();

            QuestionResponseDto questionResponseDto = QuestionResponseDto.builder()
                    .questionId(1L)
                    .questionTitle("두둥탁")
                    .questionContent("두둥두둥")
                    .createdAt(time)
                    .modifiedAt(time)
                    .tagName(getTagNameForMultiResponse())
                    .questionVoteSum(0)
                    .viewCount(1)
                    .memberName("두둥탁")
                    .build();



            return new QuestionDto.Response(questionResponseDto, getAnswerList());
        }

        public static List<AnswerDto.ResponseForList> getAnswerList() {
            LocalDateTime time = LocalDateTime.now();

            return List.of(AnswerDto.ResponseForList.builder()
                    .answerId(1L)
                    .answerContent("답변 내용")
                    .answerVoteSum(32)
                    .createdAt(time)
                    .modifiedAt(time)
                    .answerName("답변 작성자")
                    .build());
        }

        public static Page<Question> getMultiResultQuestion() {
            Question question1 = new Question();
            Question question2 = new Question();

            return new PageImpl<>(List.of(question1, question2),
                    PageRequest.of(0, 10, Sort.by("questionTitle").descending()),
                    2);
        }

        public static Page<Answer> getMultiResultAnswer() {
            Answer answer1 = new Answer();
            Answer answer2 = new Answer();

            return new PageImpl<>(List.of(answer1, answer2),
                    PageRequest.of(0, 10, Sort.by("answerVoteSum").descending()),
                    2);
        }

        public static List<QuestionDto.ResponseForList> getMultiResponseBody() {
            LocalDateTime time = LocalDateTime.now();

            return List.of(
                    QuestionDto.ResponseForList.builder()
                            .questionId(1L)
                            .questionTitle("두둥탁")
                            .viewCount(0)
                            .questionVoteSum(0)
                            .createdAt(time)
                            .tagName(getTagNameForMultiResponse())
                            .memberName("두둥탁")
                            .answerCount(0)
                            .build(),
                    QuestionDto.ResponseForList.builder()
                            .questionId(2L)
                            .questionTitle("두둥두둥")
                            .viewCount(0)
                            .questionVoteSum(0)
                            .createdAt(time)
                            .tagName(getTagNameForMultiResponse())
                            .memberName("두둥두둥")
                            .answerCount(0)
                            .build()
            );
        }
        public static List<QuestionTagDto.Response> getTagNameForMultiResponse() {
            return List.of(QuestionTagDto.Response.builder().tagId(1L).tagName("태그 1").build(),
                    QuestionTagDto.Response.builder().tagId(2L).tagName("태그 2").build());
        }
    }
}
