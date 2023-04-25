package com.dudung.preproject.questionAnswer.controller;

import com.dudung.preproject.auth.jwt.JwtTokenizer;
import com.dudung.preproject.helper.QuestionAnswerControllerHelper;
import com.dudung.preproject.helper.StubData;
import com.dudung.preproject.question.controller.QuestionAnswerController;
import com.dudung.preproject.question.domain.QuestionAnswer;
import com.dudung.preproject.question.dto.QuestionAnswerDto;
import com.dudung.preproject.question.mapper.QuestionAnswerMapper;
import com.dudung.preproject.question.service.QuestionAnswerService;
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

@WebMvcTest(QuestionAnswerController.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureRestDocs
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureMockMvc(addFilters = false) // 발급한 토큰이 유효하지 않아 붙여줌
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // BeforeAll 사전작업
public class QuestionAnswerControllerTest implements QuestionAnswerControllerHelper {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JwtTokenizer jwtTokenizer;
    @MockBean
    private QuestionAnswerService questionAnswerService;
    @MockBean
    private QuestionAnswerMapper questionAnswerMapper;

    private String accessToken;

    @BeforeAll
    public void init() {
        System.out.println("# BeforeAll");
        accessToken = StubData.MockSecurity.getValidAccessToken(jwtTokenizer.getSecretKey());
    }

    @Test
    @DisplayName("Question Answer Patch Test")
    public void updateQuestionAnswertest() throws Exception {
        QuestionAnswerDto.Patch patch = (QuestionAnswerDto.Patch) StubData.MockQuestionAnswer.getRequestBody(HttpMethod.PATCH);
        String content = toJsonContent(patch);

        given(questionAnswerMapper.questionAnswerPatchToQuestionAnswer(Mockito.any(QuestionAnswerDto.Patch.class))).willReturn(new QuestionAnswer());
        given(questionAnswerService.updateQuestionAnswer(Mockito.any(QuestionAnswer.class), Mockito.anyLong())).willReturn(new QuestionAnswer());
        ResultActions actions =
                mockMvc.perform(patchRequestBuilder(QUESTION_ANSWER_RESOURCE_URI, 1L, content, accessToken));
        actions
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("patch-questionAnswer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestHeaders(
                                getDefaultRequestHeaderDescriptor()
                        ),
                        pathParameters(
                                getMemberRequestPathParameterDescriptor()
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("questionAnswerId").type(JsonFieldType.NUMBER).description("질문 댓글 식별 번호"),
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("질문 식별 번호"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별 번호"),
                                        fieldWithPath("questionAnswerContent").type(JsonFieldType.STRING).description("질문 댓글 내용")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("Question Delete Test")
    public void deleteQuestionTest() throws Exception {
        doNothing().when(questionAnswerService).deleteQuestionAnswer(Mockito.anyLong(), Mockito.anyLong());

        mockMvc.perform(deleteRequestBuilder(QUESTION_ANSWER_RESOURCE_URI, 1L, accessToken))
                .andExpect(status().isNoContent())
                .andDo(
                        document(
                                "delete-questionAnswer",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                requestHeaders(
                                        getDefaultRequestHeaderDescriptor()
                                ),
                                pathParameters(
                                        getMemberRequestPathParameterDescriptor()
                                )
                        )
                );
    }
}
