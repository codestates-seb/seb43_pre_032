package com.dudung.preproject.question.controller;

import com.dudung.preproject.member.service.MemberService;
import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.question.dto.QuestionDto;
import com.dudung.preproject.question.dto.QuestionResponseDto;
import com.dudung.preproject.question.mapper.QuestionMapper;
import com.dudung.preproject.question.service.QuestionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import static com.dudung.preproject.utils.ApiDocumentUtils.getRequestPreProcessor;
import static com.dudung.preproject.utils.ApiDocumentUtils.getResponsePreProcessor;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuestionController.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureRestDocs
@MockBean(JpaMetamodelMappingContext.class)
public class QuestionControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private QuestionService questionService;
    @MockBean
    private QuestionMapper mapper;
    @MockBean
    private MemberService memberService;

    @Test
    @DisplayName("Question Create Test")
    public void createQuestionTest() throws  Exception {
        String content = "{\n" +
                "\"memberId\": 1,\n" +
                "\"questionTitle\": \"두둥탁\",\n" +
                "\"questionContent\": \"두둥두둥\"\n" +
                "}";

        given(mapper.questionPostToQuestion(Mockito.any(QuestionDto.Post.class))).willReturn(new Question());

        given(questionService.createQuestion(Mockito.any(Question.class))).willReturn(new Question());

        ResultActions actions =
                mockMvc.perform(
                        post("/questions")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );
        actions
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", is(startsWith("/questions"))))
                .andDo(print())
                .andDo(document("post-question",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestFields(
                                List.of(
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별 번호"),
                                        fieldWithPath("questionTitle").type(JsonFieldType.STRING).description("질문 제목"),
                                        fieldWithPath("questionContent").type(JsonFieldType.STRING).description("질문 내용")
                                )
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.LOCATION).description("Location header. 등록된 리소스의 URI")
                        )
                ));
    }

    @Test
    @DisplayName("Question Patch Test")
    public void patchQuestionTest() throws Exception {
        String content = "{\n" +
                "\"memberId\": 1,\n" +
                "\"questionId\": 1,\n" +
                "\"questionTitle\": \"두둥탁\",\n" +
                "\"questionContent\": \"두둥두둥\"\n" +
                "}";

        LocalDateTime time = LocalDateTime.now();

        QuestionResponseDto questionResponseDto = QuestionResponseDto.builder()
                .questionTitle("두둥탁")
                .questionContent("두둥두둥")
                .createdAt(time)
                .modifiedAt(time)
                .questionVoteSum(0)
                .viewCount(0)
                .memberName("두둥탁")
                .build();
        QuestionDto.Response response = new QuestionDto.Response(questionResponseDto, null);

        given(mapper.questionPatchToQuestion(Mockito.any(QuestionDto.Patch.class))).willReturn(new Question());

        given(questionService.updateQuestion(Mockito.any(Question.class))).willReturn(new Question());

        given(mapper.questionToQuestionResponse(Mockito.any(Question.class))).willReturn(response);

        ResultActions actions =
                mockMvc.perform(
                        patch("/questions/{question-id}", 1)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                );
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.question.questionTitle").value("두둥탁"))
                .andExpect(jsonPath("$.question.questionContent").value("두둥두둥"))
//                .andExpect(jsonPath("$.question.createdAt").value(time))
//                .andExpect(jsonPath("$.question.modifiedAt").value(LocalDateTime.now())) // 시간 테스팅 보류
                .andExpect(jsonPath("$.question.questionVoteSum").value(0))
                .andExpect(jsonPath("$.question.viewCount").value(0))
                .andExpect(jsonPath("$.question.memberName").value("두둥탁"))
                .andDo(print())
                .andDo(document("patch-question",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("question-id").description("질문 식별자")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별 번호").optional(),
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("질문 식별 번호").ignored(),
                                        fieldWithPath("questionTitle").type(JsonFieldType.STRING).description("질문 제목").optional(),
                                        fieldWithPath("questionContent").type(JsonFieldType.STRING).description("질문 내용").optional()
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("question.questionTitle").type(JsonFieldType.STRING).description("질문 제목").optional(),
                                        fieldWithPath("question.questionContent").type(JsonFieldType.STRING).description("질문 내용").optional(),
                                        fieldWithPath("question.createdAt").type(JsonFieldType.STRING).description("질문 생성 시간").optional(),
                                        fieldWithPath("question.modifiedAt").type(JsonFieldType.STRING).description("질문 마지막 수정 시간").optional(),
                                        fieldWithPath("question.questionVoteSum").type(JsonFieldType.NUMBER).description("질문 투표 합계").optional(),
                                        fieldWithPath("question.viewCount").type(JsonFieldType.NUMBER).description("질문 조회수").optional(),
                                        fieldWithPath("question.memberName").type(JsonFieldType.STRING).description("회원 이름").optional(),
                                        fieldWithPath("answer").type(JsonFieldType.STRING).description("답변").optional()
                                )
                        )
                ));
    }

    @Test
    @DisplayName("Question Get Test")
    public void getQuestionTest() throws Exception {
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
        QuestionDto.Response response = new QuestionDto.Response(questionResponseDto, null);

        given(questionService.findQuestion(Mockito.anyLong())).willReturn(new Question());

        given(mapper.questionToQuestionResponse(Mockito.any(Question.class))).willReturn(response);

        ResultActions actions =
                mockMvc.perform(
                        get("/questions/{question-id}", 1)
                                .accept(MediaType.APPLICATION_JSON)
                );
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.question.questionTitle").value("두둥탁"))
                .andExpect(jsonPath("$.question.questionContent").value("두둥두둥"))
//                .andExpect(jsonPath("$.question.createdAt").value(time))
//                .andExpect(jsonPath("$.question.modifiedAt").value(LocalDateTime.now())) // 시간 테스팅 보류
                .andExpect(jsonPath("$.question.questionVoteSum").value(0))
                .andExpect(jsonPath("$.question.viewCount").value(1))
                .andExpect(jsonPath("$.question.memberName").value("두둥탁"))
                .andDo(print());
    }

    @Test
    @DisplayName("Questions Get Test")
    public void getQuestionsTest() throws Exception {
        LocalDateTime time = LocalDateTime.now();

        Question question1 = new Question();
        Question question2 = new Question();

        Page<Question> response = new PageImpl<>(List.of(question1, question2), PageRequest.of(0, 10, Sort.by("questionTitle").descending()), 2);

        List<QuestionDto.ResponseForList> responseList = List.of(
            QuestionDto.ResponseForList.builder()
                    .questionTitle("두둥탁")
                    .viewCount(0)
                    .questionVoteSum(0)
                    .createdAt(time)
                    .memberName("두둥탁")
                    .answerCount(0)
                    .build(),
            QuestionDto.ResponseForList.builder()
                    .questionTitle("두둥두둥")
                    .viewCount(0)
                    .questionVoteSum(0)
                    .createdAt(time)
                    .memberName("두둥두둥")
                    .answerCount(0)
                    .build()
        );

        given(questionService.findQuestions(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString())).willReturn(response);

        given(mapper.questionsToQuestionsResponse(Mockito.anyList())).willReturn(responseList);

        String page = "1";
        String size = "10";
        String sortBy = "viewCount";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("page", page);
        params.add("size", size);
        params.add("sortBy", sortBy);

        URI uri = UriComponentsBuilder.newInstance().path("/questions").build().toUri();

        ResultActions actions =
                mockMvc.perform(
                        get(uri).params(params)
                                .accept(MediaType.APPLICATION_JSON)
                );
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andDo(print());
    }

    @Test
    @DisplayName("Question Delete Test")
    public void deleteQuestionTest() throws Exception {
        doNothing().when(questionService).deleteQuestion(Mockito.anyLong());

        mockMvc.perform(
                delete("/questions/{question-id}", 1)
                )
                .andExpect(status().isNoContent());
    }

}
