package com.dudung.preproject.answerAnswer.controller;

import com.dudung.preproject.answer.controller.AnswerAnswerController;
import com.dudung.preproject.answer.domain.AnswerAnswer;
import com.dudung.preproject.answer.dto.AnswerAnswerDto;
import com.dudung.preproject.answer.mapper.AnswerAnswerMapper;
import com.dudung.preproject.answer.service.AnswerAnswerService;
import com.dudung.preproject.answer.service.AnswerService;
import com.dudung.preproject.auth.jwt.JwtTokenizer;
import com.dudung.preproject.helper.AnswerAnswerControllerHelper;
import com.dudung.preproject.helper.StubData;
import com.dudung.preproject.member.service.MemberService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.HttpMethod;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static com.dudung.preproject.utils.ApiDocumentUtils.getRequestPreProcessor;
import static com.dudung.preproject.utils.ApiDocumentUtils.getResponsePreProcessor;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnswerAnswerController.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureRestDocs
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AnswerAnswerControllerTest implements AnswerAnswerControllerHelper {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AnswerAnswerMapper mapper;
    @MockBean
    private AnswerAnswerService answerAnswerService;

    @Autowired
    private JwtTokenizer jwtTokenizer;
    private String accessToken;

    @MockBean
    private MemberService memberService;
    @MockBean
    private AnswerService answerService;

    @BeforeAll
    public void init() {
        System.out.println("# BeforeAll");
        accessToken = StubData.MockSecurity.getValidAccessToken(jwtTokenizer.getSecretKey());
    }

    @Test
    @DisplayName("Answer Answer Post Test")
    public void createAnswerAnswerTest() throws Exception {
        AnswerAnswerDto.Post post = (AnswerAnswerDto.Post) StubData.MockAnswerAnswer.getRequestBody(HttpMethod.POST);
        String content = toJsonContent(post);

        given(mapper.answerAnswerPostToAnswerAnswer(Mockito.any(AnswerAnswerDto.Post.class))).willReturn(new AnswerAnswer());
        given(answerAnswerService.createAnswerAnswer(Mockito.any(AnswerAnswer.class), Mockito.anyLong())).willReturn(new AnswerAnswer());

        ResultActions actions =
                mockMvc.perform(postRequestBuilder(ANSWER_ANSWER_DEFAULT_URL, content, accessToken));
        actions
                .andExpect(status().isCreated())
                .andDo(print())
                .andDo(document("post-answerAnswer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestHeaders(
                                getDefaultRequestHeaderDescriptor()
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별 번호"),
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("답변 식별 번호"),
                                        fieldWithPath("answerAnswerContent").type(JsonFieldType.STRING).description("답변 댓글 내용"),
                                        fieldWithPath("createdAt").type(JsonFieldType.STRING).description("답변 댓글 작성 시간")
                                )
                        ))
                );
    }


    @Test
    @DisplayName("Answer Answer Patch Test")
    public void updateAnswerAnswerTest() throws Exception {
        AnswerAnswerDto.Patch patch = (AnswerAnswerDto.Patch) StubData.MockAnswerAnswer.getRequestBody(HttpMethod.PATCH);
        String content = toJsonContent(patch);

        given(mapper.answerAnswerPatchToAnswerAnswer(Mockito.any(AnswerAnswerDto.Patch.class))).willReturn(new AnswerAnswer());
        given(answerAnswerService.updateAnswerAnswer(Mockito.any(AnswerAnswer.class), Mockito.anyLong())).willReturn(new AnswerAnswer());
        ResultActions actions =
                mockMvc.perform(patchRequestBuilder(ANSWER_ANSWER_RESOURCE_URI, 1L, content, accessToken));
        actions
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("patch-answerAnswer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestHeaders(
                                getDefaultRequestHeaderDescriptor()
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("answerAnswerId").type(JsonFieldType.NUMBER).description("답변 댓글 식별 번호"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별 번호"),
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("댓글 식별 번호"),
                                        fieldWithPath("answerAnswerContent").type(JsonFieldType.STRING).description("답변 댓글 내용"),
                                        fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description("답변 댓글 수정 시간")
                                )
                        )
                ));
    }


    @Test
    @DisplayName("Answer Answer Delete Test")
    public void deleteAnswerAnswerTest() throws Exception {
        doNothing().when(answerAnswerService).deleteAnswerAnswer(Mockito.any(), Mockito.anyLong());

        mockMvc.perform(deleteRequestBuilder(ANSWER_ANSWER_RESOURCE_URI, 1L, accessToken))
                .andExpect(status().isNoContent())
                .andDo(document("delete-answerAnswer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestHeaders(
                                getDefaultRequestHeaderDescriptor()
                        ),
                        pathParameters(
                                getMemberRequestPathParameterDescriptor()
                        )
                ));
    }
}