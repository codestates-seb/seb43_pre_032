package com.dudung.preproject.questionvote.controller;

import com.dudung.preproject.member.domain.Member;
import com.dudung.preproject.member.service.MemberService;
import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.question.service.QuestionService;
import com.dudung.preproject.questionVote.controller.QuestionVoteController;
import com.dudung.preproject.questionVote.dto.QuestionVoteDto;
import com.dudung.preproject.questionVote.mapper.QuestionVoteMapper;
import com.dudung.preproject.questionVote.service.QuestionVoteService;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.dudung.preproject.utils.ApiDocumentUtils.getRequestPreProcessor;
import static com.dudung.preproject.utils.ApiDocumentUtils.getResponsePreProcessor;


@WebMvcTest(QuestionVoteController.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureRestDocs
@MockBean(JpaMetamodelMappingContext.class)

public class QuestionVoteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;
    @MockBean
    private QuestionVoteService questionVoteService;
    @MockBean
    private MemberService memberService;
    @MockBean
    private QuestionVoteMapper mapper;
    @MockBean
    private QuestionService questionService;

    @Test
    @DisplayName("Question Vote Up Test")
    public void questionVoteUpTest() throws Exception {

        // given
        QuestionVoteDto.QuestionVotePost post = new QuestionVoteDto.QuestionVotePost();
        post.setQuestionId(1L);
        post.setMemberId(1L);
        post.setVote(true);

        QuestionVoteDto.QuestionVoteResponse response = QuestionVoteDto.QuestionVoteResponse.builder()
                .score(1)
                .build();

        String postcontent = gson.toJson(post);



        doNothing().when(questionVoteService).questionVoteUp(Mockito.any(Member.class), Mockito.any(Question.class));
        given(mapper.questionToQuestionVoteResponse(Mockito.any(Question.class))).willReturn(response);

        // when
        ResultActions actions =
                mockMvc.perform(post("/questionvote")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postcontent));

        // then
        actions
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("post-questionvoteup",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestFields(
                                List.of(
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("질문 식별 번호"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별 번호"),
                                        fieldWithPath("vote").type(JsonFieldType.BOOLEAN).description("질문 긍정 투표")
                                )
                        )
                ));

    }


    @Test
    @DisplayName("Question Vote Down Test")
    public void questionVoteDownTest() throws Exception {

        // given
        QuestionVoteDto.QuestionVotePost post = new QuestionVoteDto.QuestionVotePost();
        post.setQuestionId(1L);
        post.setMemberId(1L);
        post.setVote(false);

        QuestionVoteDto.QuestionVoteResponse response = QuestionVoteDto.QuestionVoteResponse.builder()
                .score(1)
                .build();

        String postcontent = gson.toJson(post);


        doNothing().when(questionVoteService).questionVoteDown(Mockito.any(Member.class), Mockito.any(Question.class));
        given(mapper.questionToQuestionVoteResponse(Mockito.any(Question.class))).willReturn(response);

        // when
        ResultActions actions =
                mockMvc.perform(post("/questionvote")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postcontent));

        // then
        actions
                .andExpect(status().isOk())
                .andDo(document("post-questionvotedown",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestFields(
                                List.of(
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("회원 식별 번호"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별 번호"),
                                        fieldWithPath("vote").type(JsonFieldType.BOOLEAN).description("질문 부정 투표")
                                )
                        )

                ));
    }
}