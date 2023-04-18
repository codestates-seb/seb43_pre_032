package com.dudung.preproject.question.controller;

import com.dudung.preproject.answer.domain.Answer;
import com.dudung.preproject.answer.dto.AnswerDto;
import com.dudung.preproject.answer.service.AnswerService;
import com.dudung.preproject.helper.QuestionControllerHelper;
import com.dudung.preproject.helper.StubData;
import com.dudung.preproject.member.service.MemberService;
import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.question.dto.QuestionDto;
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
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

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
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuestionController.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureRestDocs
@MockBean(JpaMetamodelMappingContext.class)
public class QuestionControllerTest implements QuestionControllerHelper {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private QuestionService questionService;
    @MockBean
    private QuestionMapper questionMapper;
    @MockBean
    private AnswerService answerService;

    @Test
    @DisplayName("Question Create Test")
    public void createQuestionTest() throws  Exception {
        // given
        QuestionDto.Post post = (QuestionDto.Post) StubData.MockQuestion.getRequestBody(HttpMethod.POST);
        String content = toJsonContent(post);

        given(questionMapper.questionPostToQuestion(Mockito.any(QuestionDto.Post.class))).willReturn(new Question());

        Question mockResultQuestion = new Question();
        mockResultQuestion.setQuestionId(1L);
        given(questionService.createQuestion(Mockito.any(Question.class))).willReturn(mockResultQuestion);

        //when
        ResultActions actions =
                mockMvc.perform(postRequestBuilder(QUESTION_DEFAULT_URL, content));
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
                                        fieldWithPath("questionContent").type(JsonFieldType.STRING).description("질문 내용"),
                                        fieldWithPath("tagName[]").type(JsonFieldType.ARRAY).description("질문 태그 리스트"),
                                        fieldWithPath("tagName[].tagId").type(JsonFieldType.NUMBER).description("태그 식별 번호"),
                                        fieldWithPath("createdAt").type(JsonFieldType.STRING).description("질문 생성 시간")
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
        // given
        QuestionDto.Patch patch = (QuestionDto.Patch) StubData.MockQuestion.getRequestBody(HttpMethod.PATCH);
        String content = toJsonContent(patch);

        given(questionMapper.questionPatchToQuestion(Mockito.any(QuestionDto.Patch.class))).willReturn(new Question());
        given(questionService.updateQuestion(Mockito.any(Question.class))).willReturn(new Question());
        given(questionMapper.questionToQuestionResponse(Mockito.any(Question.class), Mockito.anyList())).willReturn(StubData.MockQuestion.getResponseBody());

        // when
        ResultActions actions =
                mockMvc.perform(patchRequestBuilder(QUESTION_RESOURCE_URI, 1L, content));

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.question.questionTitle").value("두둥탁"))
                .andExpect(jsonPath("$.question.questionContent").value("두둥두둥"))
//                .andExpect(jsonPath("$.question.createdAt").value(time))
//                .andExpect(jsonPath("$.question.modifiedAt").value(LocalDateTime.now())) // 시간 테스팅 보류
                .andExpect(jsonPath("$.question.questionVoteSum").value(0))
                .andExpect(jsonPath("$.question.viewCount").value(1))
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
                                        fieldWithPath("questionContent").type(JsonFieldType.STRING).description("질문 내용").optional(),
                                        fieldWithPath("tagName[]").type(JsonFieldType.ARRAY).description("질문 태그 리스트"),
                                        fieldWithPath("tagName[].tagId").type(JsonFieldType.NUMBER).description("태그 식별 번호"),
                                        fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description("질문 마지막 수정 시간")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("question.questionId").type(JsonFieldType.NUMBER).description("질문 식별 번호").optional(),
                                        fieldWithPath("question.questionTitle").type(JsonFieldType.STRING).description("질문 제목").optional(),
                                        fieldWithPath("question.questionContent").type(JsonFieldType.STRING).description("질문 내용").optional(),
                                        fieldWithPath("question.createdAt").type(JsonFieldType.STRING).description("질문 생성 시간").optional(),
                                        fieldWithPath("question.modifiedAt").type(JsonFieldType.STRING).description("질문 마지막 수정 시간").optional(),
                                        fieldWithPath("question.tagName[]").type(JsonFieldType.ARRAY).description("질문 태그 리스트"),
                                        fieldWithPath("question.tagName[].tagId").type(JsonFieldType.NUMBER).description("태그 식별 번호"),
                                        fieldWithPath("question.tagName[].tagName").type(JsonFieldType.STRING).description("태그 이름"),
                                        fieldWithPath("question.questionVoteSum").type(JsonFieldType.NUMBER).description("질문 투표 합계").optional(),
                                        fieldWithPath("question.viewCount").type(JsonFieldType.NUMBER).description("질문 조회수").optional(),
                                        fieldWithPath("question.memberName").type(JsonFieldType.STRING).description("회원 이름").optional(),
                                        fieldWithPath("answer[]").type(JsonFieldType.ARRAY).description("답변 리스트").optional(),
                                        fieldWithPath("answer[].answerId").type(JsonFieldType.NUMBER).description("답변 식별 번호").optional(),
                                        fieldWithPath("answer[].answerContent").type(JsonFieldType.STRING).description("답변 내용").optional(),
                                        fieldWithPath("answer[].answerVoteSum").type(JsonFieldType.NUMBER).description("답변 투표 합계").optional(),
                                        fieldWithPath("answer[].createdAt").type(JsonFieldType.STRING).description("답변 생성 일자").optional(),
                                        fieldWithPath("answer[].modifiedAt").type(JsonFieldType.STRING).description("답변 마지막 수정 일자").optional(),
                                        fieldWithPath("answer[].answerName").type(JsonFieldType.STRING).description("답변 작성자").optional()
                                )
                        )
                ));
    }

    @Test
    @DisplayName("Question Get Test")
    public void getQuestionTest() throws Exception {
        // given
        String page = "1";
        String size = "10";
        String sortBy = "questionId";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("page", page);
        params.add("size", size);
        params.add("sortBy", sortBy);
        Page<Answer> answers = StubData.MockQuestion.getMultiResultAnswer();
        List<AnswerDto.ResponseForList> answerList = StubData.MockQuestion.getAnswerList();

        given(questionService.findQuestion(Mockito.anyLong())).willReturn(new Question());
        given(answerService.findAnswers(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString())).willReturn(answers);
        given(questionMapper.questionToQuestionResponse(Mockito.any(Question.class), Mockito.anyList())).willReturn(StubData.MockQuestion.getResponseBody());

        // when
        ResultActions actions =
                mockMvc.perform(getRequestBuilderWithParams(QUESTION_RESOURCE_URI, 1L, params));

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.question.questionTitle").value("두둥탁"))
                .andExpect(jsonPath("$.data.question.questionContent").value("두둥두둥"))
//                .andExpect(jsonPath("$.question.createdAt").value(time))
//                .andExpect(jsonPath("$.question.modifiedAt").value(LocalDateTime.now())) // 시간 테스팅 보류
                .andExpect(jsonPath("$.data.question.questionVoteSum").value(0))
                .andExpect(jsonPath("$.data.question.viewCount").value(1))
                .andExpect(jsonPath("$.data.question.memberName").value("두둥탁"))
                .andDo(print())
                .andDo(document("get-question",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                getMemberRequestPathParameterDescriptor()
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data.question.questionId").type(JsonFieldType.NUMBER).description("질문 식별 번호").optional(),
                                        fieldWithPath("data.question.questionTitle").type(JsonFieldType.STRING).description("질문 제목").optional(),
                                        fieldWithPath("data.question.questionContent").type(JsonFieldType.STRING).description("질문 내용").optional(),
                                        fieldWithPath("data.question.createdAt").type(JsonFieldType.STRING).description("질문 생성 시간").optional(),
                                        fieldWithPath("data.question.modifiedAt").type(JsonFieldType.STRING).description("질문 마지막 수정 시간").optional(),
                                        fieldWithPath("data.question.tagName[]").type(JsonFieldType.ARRAY).description("질문 태그 리스트"),
                                        fieldWithPath("data.question.tagName[].tagId").type(JsonFieldType.NUMBER).description("태그 식별 번호"),
                                        fieldWithPath("data.question.tagName[].tagName").type(JsonFieldType.STRING).description("태그 이름"),
                                        fieldWithPath("data.question.questionVoteSum").type(JsonFieldType.NUMBER).description("질문 투표 합계").optional(),
                                        fieldWithPath("data.question.viewCount").type(JsonFieldType.NUMBER).description("질문 조회수").optional(),
                                        fieldWithPath("data.question.memberName").type(JsonFieldType.STRING).description("회원 이름").optional(),
                                        fieldWithPath("data.answer[]").type(JsonFieldType.ARRAY).description("답변 리스트").optional(),
                                        fieldWithPath("data.answer[].answerId").type(JsonFieldType.NUMBER).description("답변 식별 번호").optional(),
                                        fieldWithPath("data.answer[].answerContent").type(JsonFieldType.STRING).description("답변 내용").optional(),
                                        fieldWithPath("data.answer[].answerVoteSum").type(JsonFieldType.NUMBER).description("답변 투표 합계").optional(),
                                        fieldWithPath("data.answer[].createdAt").type(JsonFieldType.STRING).description("답변 생성 일자").optional(),
                                        fieldWithPath("data.answer[].modifiedAt").type(JsonFieldType.STRING).description("답변 마지막 수정 일자").optional(),
                                        fieldWithPath("data.answer[].answerName").type(JsonFieldType.STRING).description("답변 작성자").optional(),
                                        fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("페이지 정보"),
                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("현재 페이지"),
                                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("페이지 내 답변 갯수"),
                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("전체 답변 수"),
                                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("전체 페이지 수")

                                )
                        )
                ));
    }

    @Test
    @DisplayName("Questions Get Test")
    public void getQuestionsTest() throws Exception {
        // given
        String page = "1";
        String size = "10";
        String sortBy = "questionId";
        String keyword = "keyword";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("page", page);
        params.add("size", size);
        params.add("sortBy", sortBy);
        params.add("keyword", keyword);
        Page<Question> questions = StubData.MockQuestion.getMultiResultQuestion();
        List<QuestionDto.ResponseForList> responseList = StubData.MockQuestion.getMultiResponseBody();

        given(questionService.findQuestions(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString(), Mockito.anyString())).willReturn(questions);
        given(questionMapper.questionsToQuestionsResponse(Mockito.anyList())).willReturn(responseList);

        // when
        ResultActions actions =
                mockMvc.perform(getRequestBuilder(QUESTION_DEFAULT_URL, params));

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andDo(print())
                .andDo(document("get-questions",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestParameters(
                                List.of(
                                        parameterWithName("page").description("페이지"),
                                        parameterWithName("size").description("한 페이지에 표시 될 회원 정보 갯수"),
                                        parameterWithName("sortBy").description("정렬 기준 ex) questionId"),
                                        parameterWithName("keyword").description("검색 키워드")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data[].questionId").type(JsonFieldType.NUMBER).description("질문 식별 번호").optional(),
                                        fieldWithPath("data[].questionTitle").type(JsonFieldType.STRING).description("질문 제목").optional(),
                                        fieldWithPath("data[].createdAt").type(JsonFieldType.STRING).description("질문 생성 시간").optional(),
                                        fieldWithPath("data[].modifiedAt").type(JsonFieldType.STRING).description("질문 마지막 수정 시간").optional(),
                                        fieldWithPath("data[].tagName[]").type(JsonFieldType.ARRAY).description("질문 태그 리스트"),
                                        fieldWithPath("data[].tagName[].tagId").type(JsonFieldType.NUMBER).description("태그 식별 번호"),
                                        fieldWithPath("data[].tagName[].tagName").type(JsonFieldType.STRING).description("태그 이름"),
                                        fieldWithPath("data[].questionVoteSum").type(JsonFieldType.NUMBER).description("질문 투표 합계").optional(),
                                        fieldWithPath("data[].viewCount").type(JsonFieldType.NUMBER).description("질문 조회수").optional(),
                                        fieldWithPath("data[].memberName").type(JsonFieldType.STRING).description("회원 이름").optional(),
                                        fieldWithPath("data[].answerCount").type(JsonFieldType.NUMBER).description("답변 갯수").optional(),
                                        fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("페이지 정보"),
                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("현재 페이지"),
                                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("페이지 내 질문 갯수"),
                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("전체 질문 수"),
                                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("전체 페이지 수")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("Question Delete Test")
    public void deleteQuestionTest() throws Exception {
        doNothing().when(questionService).deleteQuestion(Mockito.anyLong());

        mockMvc.perform(deleteRequestBuilder(QUESTION_RESOURCE_URI, 1L))
                .andExpect(status().isNoContent())
                .andDo(
                        document(
                                "delete-question",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                pathParameters(
                                        getMemberRequestPathParameterDescriptor()
                                )
                        )
                );
    }

}
