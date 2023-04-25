package com.dudung.preproject.question.controller;

import com.dudung.preproject.answer.domain.Answer;
import com.dudung.preproject.answer.dto.AnswerDto;
import com.dudung.preproject.answer.service.AnswerService;
import com.dudung.preproject.auth.jwt.JwtTokenizer;
import com.dudung.preproject.helper.QuestionControllerHelper;
import com.dudung.preproject.helper.StubData;
import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.question.domain.QuestionAnswer;
import com.dudung.preproject.question.dto.QuestionAnswerDto;
import com.dudung.preproject.question.dto.QuestionDto;
import com.dudung.preproject.question.mapper.QuestionAnswerMapper;
import com.dudung.preproject.question.mapper.QuestionMapper;
import com.dudung.preproject.question.service.QuestionAnswerService;
import com.dudung.preproject.question.service.QuestionService;
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
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuestionController.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureRestDocs
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureMockMvc(addFilters = false) // 발급한 토큰이 유효하지 않아 붙여줌
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // BeforeAll 사전작업
public class QuestionControllerTest implements QuestionControllerHelper {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JwtTokenizer jwtTokenizer;
    @MockBean
    private QuestionService questionService;
    @MockBean
    private QuestionMapper questionMapper;
    @MockBean
    private AnswerService answerService;
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
    @DisplayName("Question Create Test")
    public void createQuestionTest() throws  Exception {
        // given
        QuestionDto.Post post = (QuestionDto.Post) StubData.MockQuestion.getRequestBody(HttpMethod.POST);
        String content = toJsonContent(post);

        given(questionMapper.questionPostToQuestion(Mockito.any(QuestionDto.Post.class))).willReturn(new Question());

        Question mockResultQuestion = new Question();
        mockResultQuestion.setQuestionId(1L);
        given(questionService.createQuestion(Mockito.any(Question.class), Mockito.anyLong())).willReturn(mockResultQuestion);

        //when
        ResultActions actions =
                mockMvc.perform(postRequestBuilder(QUESTION_DEFAULT_URL, content, accessToken));
        actions
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", is(startsWith("/questions"))))
                .andDo(print())
                .andDo(document("post-question",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestHeaders(
                                getDefaultRequestHeaderDescriptor()
                        ),
                        requestFields(
                                List.of(
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
        given(questionService.updateQuestion(Mockito.any(Question.class), Mockito.anyLong())).willReturn(new Question());

        // when
        ResultActions actions =
                mockMvc.perform(patchRequestBuilder(QUESTION_RESOURCE_URI, 1L, content, accessToken));

        // then
        actions
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("patch-question",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestHeaders(
                                getDefaultRequestHeaderDescriptor()
                        ),
                        pathParameters(
                                parameterWithName("question-id").description("질문 식별자")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("질문 식별 번호").ignored(),
                                        fieldWithPath("questionTitle").type(JsonFieldType.STRING).description("질문 제목").optional(),
                                        fieldWithPath("questionContent").type(JsonFieldType.STRING).description("질문 내용").optional(),
                                        fieldWithPath("tagName[]").type(JsonFieldType.ARRAY).description("질문 태그 리스트"),
                                        fieldWithPath("tagName[].tagId").type(JsonFieldType.NUMBER).description("태그 식별 번호"),
                                        fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description("질문 마지막 수정 시간")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("Question Get Test")
    public void getQuestionTest() throws Exception {
        // given
        String page = "1";
        String tab = "Newest";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("page", page);
        params.add("tab", tab);
        Page<Answer> answers = StubData.MockQuestion.getMultiResultAnswer();

        given(questionService.findQuestion(Mockito.anyLong())).willReturn(new Question());
        given(answerService.findAnswers(Mockito.anyInt(), Mockito.anyString())).willReturn(answers);
        given(questionMapper.questionToQuestionResponse(Mockito.any(Question.class), Mockito.anyList())).willReturn(StubData.MockQuestion.getResponseBody());

        // when
        ResultActions actions =
                mockMvc.perform(getRequestBuilderWithParams(QUESTION_RESOURCE_URI, 1L, params));

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.question.questionTitle").value("질문 제목"))
                .andExpect(jsonPath("$.data.question.questionContent").value("질문 내용"))
//                .andExpect(jsonPath("$.question.createdAt").value(time))
//                .andExpect(jsonPath("$.question.modifiedAt").value(LocalDateTime.now())) // 시간 테스팅 보류
                .andExpect(jsonPath("$.data.question.questionVoteSum").value(0))
                .andExpect(jsonPath("$.data.question.viewCount").value(1))
                .andExpect(jsonPath("$.data.question.memberName").value("질문 작성자"))
                .andDo(print())
                .andDo(document("get-question",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                getMemberRequestPathParameterDescriptor()
                        ),
                        requestParameters(
                                List.of(
                                        parameterWithName("page").description("페이지"),
                                        parameterWithName("tab").description("정렬 기준")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data.question.questionId").type(JsonFieldType.NUMBER).description("질문 식별 번호").optional(),
                                        fieldWithPath("data.question.memberId").type(JsonFieldType.NUMBER).description("회원 식별 번호").optional(),
                                        fieldWithPath("data.question.questionTitle").type(JsonFieldType.STRING).description("질문 제목").optional(),
                                        fieldWithPath("data.question.questionContent").type(JsonFieldType.STRING).description("질문 내용").optional(),
                                        fieldWithPath("data.question.createdAt").type(JsonFieldType.STRING).description("질문 생성 시간").optional(),
                                        fieldWithPath("data.question.modifiedAt").type(JsonFieldType.STRING).description("질문 마지막 수정 시간").optional(),
                                        fieldWithPath("data.question.tagName[]").type(JsonFieldType.ARRAY).description("질문 태그 리스트"),
                                        fieldWithPath("data.question.tagName[].tagId").type(JsonFieldType.NUMBER).description("태그 식별 번호"),
                                        fieldWithPath("data.question.tagName[].tagName").type(JsonFieldType.STRING).description("태그 이름"),
                                        fieldWithPath("data.question.questionAnswers[]").type(JsonFieldType.ARRAY).description("질문 댓글 리스트"),
                                        fieldWithPath("data.question.questionAnswers[].questionAnswerId").type(JsonFieldType.NUMBER).description("질문 댓글 식별 번호"),
                                        fieldWithPath("data.question.questionAnswers[].questionAnswerContent").type(JsonFieldType.STRING).description("질문 댓글 내용"),
                                        fieldWithPath("data.question.questionAnswers[].createdAt").type(JsonFieldType.STRING).description("질문 댓글 생성 시간"),
                                        fieldWithPath("data.question.questionAnswers[].modifiedAt").type(JsonFieldType.STRING).description("질문 댓글 마지막 수정 시간"),
                                        fieldWithPath("data.question.questionAnswers[].memberId").type(JsonFieldType.NUMBER).description("회원 식별 번호"),
                                        fieldWithPath("data.question.questionAnswers[].memberName").type(JsonFieldType.STRING).description("질문 댓글 작성자"),
                                        fieldWithPath("data.question.questionAnswers[].memberReputation").type(JsonFieldType.NUMBER).description("회원 명성도"),
                                        fieldWithPath("data.question.questionVoteSum").type(JsonFieldType.NUMBER).description("질문 투표 합계").optional(),
                                        fieldWithPath("data.question.viewCount").type(JsonFieldType.NUMBER).description("질문 조회수").optional(),
                                        fieldWithPath("data.question.memberName").type(JsonFieldType.STRING).description("질문 작성자").optional(),
                                        fieldWithPath("data.question.memberReputation").type(JsonFieldType.NUMBER).description("회원 명성도").optional(),
                                        fieldWithPath("data.answer[]").type(JsonFieldType.ARRAY).description("답변 리스트").optional(),
                                        fieldWithPath("data.answer[].answerId").type(JsonFieldType.NUMBER).description("답변 식별 번호").optional(),
                                        fieldWithPath("data.answer[].memberId").type(JsonFieldType.NUMBER).description("회원 식별 번호").optional(),
                                        fieldWithPath("data.answer[].memberName").type(JsonFieldType.STRING).description("답변 작성자").optional(),
                                        fieldWithPath("data.answer[].answerContent").type(JsonFieldType.STRING).description("답변 내용").optional(),
                                        fieldWithPath("data.answer[].answerVoteSum").type(JsonFieldType.NUMBER).description("답변 투표 합계").optional(),
                                        fieldWithPath("data.answer[].createdAt").type(JsonFieldType.STRING).description("답변 생성 일자").optional(),
                                        fieldWithPath("data.answer[].modifiedAt").type(JsonFieldType.STRING).description("답변 마지막 수정 일자").optional(),
                                        fieldWithPath("data.answer[].answerName").type(JsonFieldType.STRING).description("답변 작성자").optional(),
                                        fieldWithPath("data.answer[].memberReputation").type(JsonFieldType.NUMBER).description("회원 명성도").optional(),
                                        fieldWithPath("data.answer[].answerAnswers[]").type(JsonFieldType.ARRAY).description("답변 댓글 리스트"),
                                        fieldWithPath("data.answer[].answerAnswers[].answerAnswerId").type(JsonFieldType.NUMBER).description("답변 댓글 식별 번호"),
                                        fieldWithPath("data.answer[].answerAnswers[].answerAnswerContent").type(JsonFieldType.STRING).description("답변 댓글 내용"),
                                        fieldWithPath("data.answer[].answerAnswers[].createdAt").type(JsonFieldType.STRING).description("답변 댓글 생성 시간"),
                                        fieldWithPath("data.answer[].answerAnswers[].modifiedAt").type(JsonFieldType.STRING).description("답변 댓글 마지막 수정 시간"),
                                        fieldWithPath("data.answer[].answerAnswers[].memberId").type(JsonFieldType.NUMBER).description("회원 식별 번호"),
                                        fieldWithPath("data.answer[].answerAnswers[].memberName").type(JsonFieldType.STRING).description("답변 댓글 작성자"),
                                        fieldWithPath("data.answer[].answerAnswers[].memberReputation").type(JsonFieldType.NUMBER).description("회원 명성도"),
                                        fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("페이지 정보"),
                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("현재 페이지"),
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
        String tab = "Newest";
        String keyword = "keyword";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("page", page);
        params.add("tab", tab);
        params.add("keyword", keyword);
        Page<Question> questions = StubData.MockQuestion.getMultiResultQuestion();
        List<QuestionDto.ResponseForList> responseList = StubData.MockQuestion.getMultiResponseBody();

        given(questionService.findQuestions(Mockito.anyInt(), Mockito.anyString(), Mockito.anyString())).willReturn(questions);
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
                                        parameterWithName("tab").description("정렬 기준 Newest, Active, Score"),
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
                                        fieldWithPath("data[].lastStatus").type(JsonFieldType.STRING).description("질문의 마지막 상태"),
                                        fieldWithPath("data[].lastStatusTime").type(JsonFieldType.STRING).description("질문의 마지막 상태로 전환된 시간"),
                                        fieldWithPath("data[].questionVoteSum").type(JsonFieldType.NUMBER).description("질문 투표 합계").optional(),
                                        fieldWithPath("data[].viewCount").type(JsonFieldType.NUMBER).description("질문 조회수").optional(),
                                        fieldWithPath("data[].memberName").type(JsonFieldType.STRING).description("질문 작성자").optional(),
                                        fieldWithPath("data[].answerCount").type(JsonFieldType.NUMBER).description("답변 갯수").optional(),
                                        fieldWithPath("data[].questionContent").type(JsonFieldType.STRING).description("질문 내용").optional(),
                                        fieldWithPath("data[].memberId").type(JsonFieldType.NUMBER).description("회원 식별 번호").optional(),
                                        fieldWithPath("data[].memberReputation").type(JsonFieldType.NUMBER).description("회원 명성도").optional(),
                                        fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("페이지 정보"),
                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("현재 페이지"),
                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("전체 질문 수"),
                                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("전체 페이지 수")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("Question Delete Test")
    public void deleteQuestionTest() throws Exception {
        doNothing().when(questionService).deleteQuestion(Mockito.anyLong(), Mockito.anyLong());

        mockMvc.perform(deleteRequestBuilder(QUESTION_RESOURCE_URI, 1L, accessToken))
                .andExpect(status().isNoContent())
                .andDo(
                        document(
                                "delete-question",
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

    @Test
    @DisplayName("Question Answer Create Test")
    public void createQuestionAnswertest() throws Exception {
        QuestionAnswerDto.Post post = (QuestionAnswerDto.Post) StubData.MockQuestionAnswer.getRequestBody(HttpMethod.POST);
        String content = toJsonContent(post);

        given(questionAnswerMapper.questionAnswerPostToQuestionAnswer(Mockito.any(QuestionAnswerDto.Post.class))).willReturn(new QuestionAnswer());
        given(questionAnswerService.createQuestionAnswer(Mockito.any(QuestionAnswer.class), Mockito.anyLong())).willReturn(new QuestionAnswer());

        ResultActions actions =
                mockMvc.perform(postRequestBuilder(QUESTION_RESOURCE_URI, 1L, content, accessToken));
        actions
                .andExpect(status().isCreated())
                .andDo(print())
                .andDo(document("post-questionAnswer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestHeaders(
                                getDefaultRequestHeaderDescriptor()
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("질문 식별 번호"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별 번호"),
                                        fieldWithPath("questionAnswerContent").type(JsonFieldType.STRING).description("질문 댓글 내용")
                                )
                        )
                ));
    }
}
