package com.dudung.preproject.answervote.controller;

import com.dudung.preproject.answer.domain.Answer;
import com.dudung.preproject.answer.service.AnswerService;
import com.dudung.preproject.answerVote.controller.AnswerVoteController;
import com.dudung.preproject.answerVote.dto.AnswerVoteDto;
import com.dudung.preproject.answerVote.mapper.AnswerVoteMapper;
import com.dudung.preproject.answerVote.service.AnswerVoteService;
import com.dudung.preproject.member.domain.Member;
import com.dudung.preproject.member.service.MemberService;
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

import static com.dudung.preproject.utils.ApiDocumentUtils.getRequestPreProcessor;
import static com.dudung.preproject.utils.ApiDocumentUtils.getResponsePreProcessor;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnswerVoteController.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureRestDocs
@MockBean(JpaMetamodelMappingContext.class)
public class AnswerVoteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;

    @MockBean
    private AnswerVoteService answerVoteService;
    @MockBean
    private AnswerVoteMapper mapper;
    @MockBean
    private AnswerService answerService;
    @MockBean
    private MemberService memberService;

    @Test
    @DisplayName("Answer Vote up Test")
    public void answerVoteUpTest() throws Exception {

        // given
        AnswerVoteDto.Post post = new AnswerVoteDto.Post();
        post.setAnswerId(1L);
        post.setMemberId(1L);
        post.setVote(true);

        AnswerVoteDto.Response response = AnswerVoteDto.Response.builder()
                .score(1)
                .build();

        String postcontent = gson.toJson(post);


        doNothing().when(answerVoteService).voteUp(Mockito.any(Member.class), Mockito.any(Answer.class));
        given(mapper.answerVoteToAnswerVoteResponse(Mockito.any(Answer.class))).willReturn(response);

        // when
        ResultActions actions =
                mockMvc.perform(post("/answervote")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postcontent));

        // then
        actions
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("post-answervoteup",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestFields(
                                List.of(
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("답변 식별 번호"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별 번호"),
                                        fieldWithPath("vote").type(JsonFieldType.BOOLEAN).description("답변 긍정 투표")
                                )
                        )
                ));

    }

    @Test
    @DisplayName("Answer Vote Down Test")
    public void answerVoteDownTest() throws Exception {

        // given
        AnswerVoteDto.Post post = new AnswerVoteDto.Post();
        post.setAnswerId(1L);
        post.setMemberId(1L);
        post.setVote(false);

        AnswerVoteDto.Response response = AnswerVoteDto.Response.builder()
                .score(1)
                .build();

        String postcontent = gson.toJson(post);

        doNothing().when(answerVoteService).voteDown(Mockito.any(Member.class), Mockito.any(Answer.class));
        given(mapper.answerVoteToAnswerVoteResponse(Mockito.any(Answer.class))).willReturn(response);

        // when
        ResultActions actions =
                mockMvc.perform(post("/answervote")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postcontent)
                );

        // then
        actions
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("post-answervotedown",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestFields(
                                List.of(
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("답변 식별 번호"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별 번호"),
                                        fieldWithPath("vote").type(JsonFieldType.BOOLEAN).description("답변 부정 투표")
                                )
                        )
                ));
    }

}
