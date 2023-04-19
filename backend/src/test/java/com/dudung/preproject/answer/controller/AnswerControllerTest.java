package com.dudung.preproject.answer.controller;

import com.dudung.preproject.answer.domain.Answer;
import com.dudung.preproject.answer.dto.AnswerDto;
import com.dudung.preproject.answer.mapper.AnswerMapper;
import com.dudung.preproject.answer.service.AnswerService;
import com.dudung.preproject.helper.AnswerControllerHelper;
import com.dudung.preproject.helper.StubData;
import com.dudung.preproject.member.service.MemberService;
import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.question.service.QuestionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import java.time.LocalDateTime;
import java.util.List;
import com.google.gson.Gson;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnswerController.class)
@ExtendWith(MockitoExtension.class)
@MockBean(JpaMetamodelMappingContext.class)
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
        given(answerService.createAnswer(Mockito.any(Answer.class))).willReturn(mockResultAnswer);


        ResultActions actions =
                mockMvc.perform(postRequestBuilder(ANSWER_DEFAULT_URL, content));

        actions
                .andExpect(status().isCreated());
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
                        .answerName("Tester")
                        .build()
                );

        given(mapper.answerPatchAnswer(Mockito.any(AnswerDto.Patch.class))).willReturn(new Answer());
        given(answerService.updateAnswer(Mockito.any(Answer.class))).willReturn(new Answer());
        given(mapper.answerToAnswerResponse(Mockito.anyList())).willReturn(answers);

        ResultActions actions =
                mockMvc.perform(patchRequestBuilder(ANSWER_RESOURCE_URI, 1, content));

        actions
                .andExpect(status().isOk());
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
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Answer Delete Test")
    public void deleteAnswerTest() throws Exception{


        doNothing().when(answerService).deleteAnswer(Mockito.anyLong());

        ResultActions actions =
                mockMvc.perform(deleteRequestBuilder(ANSWER_RESOURCE_URI, 1));

        actions.andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Answers Get Test2")
    public void getAnswersTest() throws Exception{

        String page = "1";
        String size = "10";
        String sortBy = "answerVoteSum";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("page", page);
        params.add("size", size);
        params.add("sortBy", sortBy);

        Page<Answer> answers = StubData.MockQuestion.getMultiResultAnswer();
        List<AnswerDto.ResponseForList> answerList = StubData.MockQuestion.getAnswerList();

        given(answerService.findAnswers(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString())).willReturn(answers);
        given(mapper.answerToAnswerResponse(Mockito.anyList())).willReturn(answerList);

        ResultActions actions =
                mockMvc.perform(getRequestBuilder(ANSWER_DEFAULT_URL, params));

        actions.andExpect(status().isOk());
    }








}