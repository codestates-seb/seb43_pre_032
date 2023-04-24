package com.dudung.preproject.helper;

import com.dudung.preproject.answer.domain.Answer;
import com.dudung.preproject.answer.dto.AnswerAnswerDto;
import com.dudung.preproject.answer.dto.AnswerDto;
import com.dudung.preproject.auth.jwt.JwtTokenizer;
import com.dudung.preproject.member.domain.Member;
import com.dudung.preproject.member.dto.MemberDto;
import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.question.dto.QuestionAnswerDto;
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
import java.util.*;

public class StubData {
    public static class MockSecurity {
        // 실제 사용할 수 있는 유효한 JWT를 생성한다.
        // 하지만 유효하지 않다;;
        public static String getValidAccessToken(String secretKey) {
            JwtTokenizer jwtTokenizer = new JwtTokenizer();
            Map<String, Object> claims = new HashMap<>();
            claims.put("memberId", 1L);

            String subject = "test access token";
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, 1);
            Date expiration = calendar.getTime();

            String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(secretKey);

            String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

            return accessToken;
        }
    }
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

    public static class MockMember {
        private static Map<HttpMethod, Object> stubRequestBody;
        static {

            MemberDto.Post post = new MemberDto.Post();
            post.setEmail("12@12.kr");
            post.setPassword("123");
            post.setName("가나다");

            MemberDto.Patch patch = new MemberDto.Patch();
            patch.setMemberId(1L);
            patch.setEmail("12@12.kr");
            patch.setPassword("123");
            post.setName("가나다");

            stubRequestBody = new HashMap<>();
            stubRequestBody.put(HttpMethod.POST, post);
            stubRequestBody.put(HttpMethod.PATCH, patch);
        }

        public static Object getRequestBody(HttpMethod method) {
            return stubRequestBody.get(method);
        }

        public static Page<Member> getMultiResultMember() {
            Member member1 = new Member();
            Member member2 = new Member();

            return new PageImpl<>(List.of(member1, member2),
                    PageRequest.of(0, 10, Sort.by("memberId").descending()),
                    2);
        }

        public static List<MemberDto.ResponseForList> getMemberList() {

            return List.of(
                    MemberDto.ResponseForList.builder()
                            .memberId(1L)
                            .name("가나다")
                            .build(),
                    MemberDto.ResponseForList.builder()
                            .memberId(2L)
                            .name("마바사")
                            .build()
            );

        }
    }

    public static class MockQuestion {
        private static Map<HttpMethod, Object> stubRequestBody;
        static {

            QuestionDto.Post post = new QuestionDto.Post();
            post.setQuestionTitle("질문 제목");
            post.setQuestionContent("질문 내용");
            post.setTagName(getTagName());

            QuestionDto.Patch patch = new QuestionDto.Patch();
            patch.setQuestionId(1L);
            patch.setQuestionTitle("질문 제목");
            patch.setQuestionContent("질문 내용");
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
                    .questionTitle("질문 제목")
                    .questionContent("질문 내용")
                    .createdAt(time)
                    .modifiedAt(time)
                    .tagName(getTagNameForMultiResponse())
                    .questionAnswers(getQuestionAnswers())
                    .questionVoteSum(0)
                    .viewCount(1)
                    .memberName("질문 작성자")
                    .build();



            return new QuestionDto.Response(questionResponseDto, getAnswerList());
        }

        public static List<QuestionAnswerDto.Response> getQuestionAnswers() {
            LocalDateTime time = LocalDateTime.now();

            return List.of(
                    QuestionAnswerDto.Response.builder()
                            .questionAnswerId(1L)
                            .questionAnswerContent("질문 댓글")
                            .createdAt(time)
                            .modifiedAt(time)
                            .memberId(1L)
                            .memberName("질문 댓글 작성자")
                            .memberReputation(1).build()
            );
        }

        public static List<AnswerDto.ResponseForList> getAnswerList() {
            LocalDateTime time = LocalDateTime.now();

            return List.of(AnswerDto.ResponseForList.builder()
                    .answerId(1L)
                    .answerContent("답변 내용")
                    .answerVoteSum(32)
                    .createdAt(time)
                    .modifiedAt(time)
                    .memberName("답변 작성자")
                    .answerAnswers(getAnswerAnswers())
                    .build());
        }
        public static List<AnswerAnswerDto.Response> getAnswerAnswers() {
            LocalDateTime time = LocalDateTime.now();
            return List.of(AnswerAnswerDto.Response.builder()
                    .answerAnswerId(1L)
                    .answerAnswerContent("답변 댓글 내용")
                    .createdAt(time)
                    .modifiedAt(time)
                    .memberId(1L)
                    .memberName("답변 댓글 작성자")
                    .memberReputation(1)
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
                            .questionTitle("제목 1")
                            .viewCount(0)
                            .questionVoteSum(0)
                            .createdAt(time)
                            .tagName(getTagNameForMultiResponse())
                            .lastStatus(Question.LastStatus.QUESTION_CREATE)
                            .lastStatusTime(time)
                            .memberName("질문 작성자")
                            .answerCount(0)
                            .build(),
                    QuestionDto.ResponseForList.builder()
                            .questionId(2L)
                            .questionTitle("제목 2")
                            .viewCount(0)
                            .questionVoteSum(0)
                            .createdAt(time)
                            .tagName(getTagNameForMultiResponse())
                            .lastStatus(Question.LastStatus.QUESTION_CREATE)
                            .lastStatusTime(time)
                            .memberName("질문 작성자")
                            .answerCount(0)
                            .build()
            );
        }
        public static List<QuestionTagDto.Response> getTagNameForMultiResponse() {
            return List.of(QuestionTagDto.Response.builder().tagId(1L).tagName("태그 1").build(),
                    QuestionTagDto.Response.builder().tagId(2L).tagName("태그 2").build());
        }
    }

    public static class MockTag {

        public static TagDto.Response getResponseBody() {
            LocalDateTime time = LocalDateTime.now();

            List<QuestionTagDto.Response> responseQuestionTag = List.of(
                    QuestionTagDto.Response.builder()
                            .tagId(1)
                            .tagName("태그 1")
                            .build()
            );

            List<QuestionDto.ResponseForList> responseQuestions = List.of(
                    QuestionDto.ResponseForList.builder()
                            .questionId(1L)
                            .questionTitle("질문 제목")
                            .viewCount(1)
                            .questionVoteSum(1)
                            .createdAt(time)
                            .tagName(responseQuestionTag)
                            .lastStatus(Question.LastStatus.QUESTION_CREATE)
                            .lastStatusTime(time)
                            .memberName("작성자 이름")
                            .questionContent("질문 내용")
                            .answerCount(1)
                            .build()
            );

            return TagDto.Response.builder()
                        .tagId(1)
                        .tagName("태그 1")
                        .tagDescription("태그 1 설명")
                        .questions(responseQuestions)
                        .build();
        }

        public static Page<Tag> getMultiResultTag() {
            Tag tag1 = new Tag();
            Tag tag2 = new Tag();
            return new PageImpl<>(List.of(tag1, tag2),
                    PageRequest.of(0, 10, Sort.by("tagId").ascending()),
                    2);
        }

        public static List<TagDto.ResponseForList> getMultiResponseBody() {
            return List.of(
            TagDto.ResponseForList.builder()
                    .tagId(1L)
                    .tagName("태그 1")
                    .tagDescription("태그 1 설명")
                    .build(),
            TagDto.ResponseForList.builder()
                    .tagId(2L)
                    .tagName("태그 2")
                    .tagDescription("태그 2 설명")
                    .build());
        }
    }
    public static class MockQuestionAnswer {
        private static Map<HttpMethod, Object> stubRequestBody;
        static {
            QuestionAnswerDto.Post post = new QuestionAnswerDto.Post();
            post.setQuestionId(1L);
            post.setMemberId(1L);
            post.setQuestionAnswerContent("질문 댓글 내용");

            QuestionAnswerDto.Patch patch = new QuestionAnswerDto.Patch();
            patch.setQuestionAnswerId(1L);
            patch.setQuestionId(1L);
            patch.setMemberId(1L);
            patch.setQuestionAnswerContent("질문 댓글 내용");

            stubRequestBody = new HashMap<>();
            stubRequestBody.put(HttpMethod.POST, post);
            stubRequestBody.put(HttpMethod.PATCH, patch);
        }
        public static Object getRequestBody(HttpMethod method) {
            return stubRequestBody.get(method);
        }
    }
}
