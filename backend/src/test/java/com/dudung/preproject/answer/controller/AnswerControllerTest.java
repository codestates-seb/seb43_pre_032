package com.dudung.preproject.answer.controller;

import com.dudung.preproject.answer.domain.Answer;
import com.dudung.preproject.answer.dto.AnswerDto;
import com.dudung.preproject.answer.mapper.AnswerMapper;
import com.dudung.preproject.answer.service.AnswerService;
import com.dudung.preproject.auth.jwt.JwtTokenizer;
import com.dudung.preproject.helper.AnswerControllerHelper;
import com.dudung.preproject.helper.StubData;
import com.dudung.preproject.member.service.MemberService;
import com.dudung.preproject.question.domain.Question;
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
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import java.time.LocalDateTime;
import java.util.List;
import com.google.gson.Gson;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static com.dudung.preproject.utils.ApiDocumentUtils.getRequestPreProcessor;
import static com.dudung.preproject.utils.ApiDocumentUtils.getResponsePreProcessor;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AnswerController.class)
@ExtendWith(MockitoExtension.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AnswerControllerTest implements AnswerControllerHelper {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AnswerService answerService;
    @MockBean
    private MemberService memberService;
    @MockBean
    private QuestionService questionService;
    @MockBean
    private AnswerMapper mapper;
    @Autowired
    private Gson gson;
    @Autowired
    private JwtTokenizer jwtTokenizer;
    private String accessToken;

    @BeforeAll
    public void init() {
        accessToken = StubData.MockSecurity.getValidAccessToken(jwtTokenizer.getSecretKey());
    }

    @Test
    @DisplayName("Answer Create Test")
    public void createAnswerTest() throws Exception{

        //given
        AnswerDto.Post post = new AnswerDto.Post();
        post.setMemberId(1L);
        post.setQuestionId(1L);
        post.setAnswerContent("Answer.Post 테스트");
        String content = gson.toJson(post);

        given(mapper.answerPostToAnswer(Mockito.any(AnswerDto.Post.class))).willReturn(new Answer());
        Answer mockResultAnswer = new Answer();
        mockResultAnswer.setAnswerId(1L);
        given(answerService.createAnswer(Mockito.any(Answer.class), Mockito.anyLong())).willReturn(mockResultAnswer);


        ResultActions actions =
                mockMvc.perform(postRequestBuilder(ANSWER_DEFAULT_URL, content, accessToken));

        actions
                .andExpect(status().isCreated())
                .andDo(print())
                .andDo(document("post-answer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestFields(
                                List.of(
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별 번호"),
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("질문 식별 번호"),
                                        fieldWithPath("answerContent").type(JsonFieldType.STRING).description("질문 내용")
                                )
                        )));
    }


    @Test
    @DisplayName("Answer Patch Test")
    public void patchAnswerTest() throws Exception {
        AnswerDto.Patch patch = new AnswerDto.Patch();
        patch.setMemberId(1L);
        patch.setQuestion(new Question());
        patch.setAnswerId(1L);
        patch.setAnswerContent("Answer Patch Test");
        patch.setModifiedAt(LocalDateTime.now());

        String content = toJsonContent(patch);

        AnswerDto.Response response = AnswerDto.Response.builder()
                .answerId(1L)
                .questionId(1L)
                .memberName("테스터")
                .answerContent("Answer Patch Test")
                .modifiedAt(LocalDateTime.now())
                .build();

        List<AnswerDto.ResponseForList> answers = List.of(
                AnswerDto.ResponseForList.builder()
                        .answerId(1L)
                        .answerContent("Answer Patch Test")
                        .answerVoteSum(0)
                        .createdAt(LocalDateTime.now())
                        .modifiedAt(LocalDateTime.now())
                        .memberName("Tester")
                        .build()
                );

        given(mapper.answerPatchAnswer(Mockito.any(AnswerDto.Patch.class))).willReturn(new Answer());
        given(answerService.updateAnswer(Mockito.any(Answer.class), Mockito.anyLong())).willReturn(new Answer());
        given(mapper.answerToAnswerResponse(Mockito.anyList())).willReturn(answers);

        ResultActions actions =
                mockMvc.perform(patchRequestBuilder(ANSWER_RESOURCE_URI, 1L, content, accessToken));

        actions
                .andExpect(status().isOk())
                .andDo(document("patch-answer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestHeaders(
                          getDefaultRequestHeaderDescriptor()
                        ),
                        pathParameters(
                                parameterWithName("answer-id").description("답변 식별 번호")
                        ),
                        requestFields(
                                List.of(
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별 번호"),
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("질문 식별 번호"),
                                        fieldWithPath("answerContent").type(JsonFieldType.STRING).description("질문 내용"),
                                        fieldWithPath("question.viewCount").type(JsonFieldType.NUMBER).description("질문 조회수"),
                                        fieldWithPath("question.answerCount").type(JsonFieldType.NUMBER).description("답변 수"),
                                        fieldWithPath("question.questionVotes[]").type(JsonFieldType.ARRAY).description("질문 투표수"),
                                        fieldWithPath("question.questionVoteSum").type(JsonFieldType.NUMBER).description("질문 투표 합계"),
                                        fieldWithPath("question.questionTags[]").type(JsonFieldType.ARRAY).description("질문 태크 목록"),
                                        fieldWithPath("question.answers[]").type(JsonFieldType.ARRAY).description("답변 목록"),
                                        fieldWithPath("question.questionAnswers[]").type(JsonFieldType.ARRAY).description("질문 댓글 목록"),
                                        fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description("답변 수정 시간")

                                )
                        )
                ));
    }


    @Test
    @DisplayName("Answer Get Test")
    public void getAnswerTest() throws Exception {

        // given
        AnswerDto.Response response = AnswerDto.Response.builder()
                .answerId(1L)
                .questionId(1L)
                .memberName("테스터")
                .answerContent("Answer Get Test")

                .modifiedAt(LocalDateTime.now())
                .build();


        // when
        given(answerService.findAnswer(Mockito.anyLong())).willReturn(new Answer());
        given(mapper.answerToAnswerResponse(Mockito.any(Answer.class))).willReturn(response);


        ResultActions actions =
                mockMvc.perform(getRequestBuilder(ANSWER_RESOURCE_URI, 1));

        // then

        actions
                .andExpect(status().isOk())
                .andDo(document("get-answer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        responseFields(
                                List.of(
                                        fieldWithPath("answerId").type(JsonFieldType.NUMBER).description("답변 식별 번호"),
                                        fieldWithPath("questionId").type(JsonFieldType.NUMBER).description("질문 식별 번호"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별 번호"),
                                        fieldWithPath("memberName").type(JsonFieldType.STRING).description("회원명"),
                                        fieldWithPath("memberReputation").type(JsonFieldType.NUMBER).description("회원 명성도"),
                                        fieldWithPath("answerContent").type(JsonFieldType.STRING).description("답변 내용"),
                                        fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description("답변 수정 시간")

                                )
                        )
                ));

    }

    @Test
    @DisplayName("Answer Delete Test")
    public void deleteAnswerTest() throws Exception{


        doNothing().when(answerService).deleteAnswer(Mockito.anyLong(), Mockito.anyLong());

        ResultActions actions =
                mockMvc.perform(deleteRequestBuilder(ANSWER_RESOURCE_URI, 1L, accessToken));

        actions.andExpect(status().isNoContent())
               .andDo(document("delete-answer",
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

    @Test
    @DisplayName("Answers Get Test2")
    public void getAnswersTest() throws Exception{

        String page = "1";
        String tab = "answerVoteSum";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("page", page);
        params.add("tab", tab);

        Page<Answer> answers = StubData.MockQuestion.getMultiResultAnswer();
        List<AnswerDto.ResponseForList> answerList = StubData.MockQuestion.getAnswerList();

        given(answerService.findAnswers(Mockito.anyInt(), Mockito.anyString())).willReturn(answers);
        given(mapper.answerToAnswerResponse(Mockito.anyList())).willReturn(answerList);

        ResultActions actions =
                mockMvc.perform(getRequestBuilder(ANSWER_DEFAULT_URL, params));

        actions.andExpect(status().isOk())
                .andDo(document("get-answer",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestParameters(
                                List.of(
                                        parameterWithName("page").description("페이지"),
                                        parameterWithName("tab").description("정렬 기준")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data[].answerId").type(JsonFieldType.NUMBER).description("답변 식별 번호"),
                                        fieldWithPath("data[].answerContent").type(JsonFieldType.STRING).description("답변 내용"),
                                        fieldWithPath("data[].answerVoteSum").type(JsonFieldType.NUMBER).description("답변 투표 합계"),
                                        fieldWithPath("data[].createdAt").type(JsonFieldType.STRING).description("답변 생성 시간"),
                                        fieldWithPath("data[].modifiedAt").type(JsonFieldType.STRING).description("답변 수정 시간"),
                                        fieldWithPath("data[].memberId").type(JsonFieldType.NUMBER).description("회원 식별 번호"),
                                        fieldWithPath("data[].memberName").type(JsonFieldType.STRING).description("회원명"),
                                        fieldWithPath("data[].memberReputation").type(JsonFieldType.NUMBER).description("회원 명성도"),
                                        fieldWithPath("data[].answerAnswers[].answerAnswerId").type(JsonFieldType.NUMBER).description("답변 댓글 식별 번호"),
                                        fieldWithPath("data[].answerAnswers[].answerAnswerContent").type(JsonFieldType.STRING).description("답변 댓글 내용"),
                                        fieldWithPath("data[].answerAnswers[].createdAt").type(JsonFieldType.STRING).description("답변 댓글 생성 시간"),
                                        fieldWithPath("data[].answerAnswers[].modifiedAt").type(JsonFieldType.STRING).description("답변 댓글 수정 시간"),
                                        fieldWithPath("data[].answerAnswers[].memberId").type(JsonFieldType.NUMBER).description("회원 식별 변호 "),
                                        fieldWithPath("data[].answerAnswers[].memberName").type(JsonFieldType.STRING).description("답변 댓글 작성자"),
                                        fieldWithPath("data[].answerAnswers[].memberReputation").type(JsonFieldType.NUMBER).description("회원 명성도"),
                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("페이지 정보"),
                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("전체 답변 수"),
                                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("전체 페이지 수")

                                )
                        )
                ));
    }

}