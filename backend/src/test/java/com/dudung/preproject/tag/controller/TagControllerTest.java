package com.dudung.preproject.tag.controller;

import com.dudung.preproject.helper.StubData;
import com.dudung.preproject.helper.TagControllerHelper;
import com.dudung.preproject.question.domain.Question;
import com.dudung.preproject.question.service.QuestionService;
import com.dudung.preproject.tag.domain.Tag;
import com.dudung.preproject.tag.dto.TagDto;
import com.dudung.preproject.tag.mapper.TagMapper;
import com.dudung.preproject.tag.service.TagService;
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
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static com.dudung.preproject.utils.ApiDocumentUtils.getRequestPreProcessor;
import static com.dudung.preproject.utils.ApiDocumentUtils.getResponsePreProcessor;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TagController.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureRestDocs
@MockBean(JpaMetamodelMappingContext.class)
public class TagControllerTest implements TagControllerHelper {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TagService tagService;
    @MockBean
    private TagMapper tagMapper;
    @MockBean
    private QuestionService questionService;

    @Test
    @DisplayName("Tag Get Test")
    public void getTagTest() throws Exception {
        // given
        String page = "1";
        String size = "10";
        String sortBy = "tagId";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("page", page);
        params.add("size", size);
        params.add("sortBy", sortBy);

        TagDto.Response response = StubData.MockTag.getResponseBody();
        Page<Question> questions = StubData.MockQuestion.getMultiResultQuestion();

        given(tagService.findTag(Mockito.anyLong())).willReturn(new Tag());
        given(questionService.findQuestions(Mockito.any(Tag.class), Mockito.anyInt(), Mockito.anyString())).willReturn(questions);
        given(tagMapper.tagTotagResponse(Mockito.any(Tag.class), Mockito.anyList())).willReturn(response);

        // when
        ResultActions actions =
                mockMvc.perform(getRequestBuilderWithParams(TAG_RESOURCE_URI, 1L, params));

        // then
        actions
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("get-tag",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                parameterWithName("tag-id").description("태그 식별 번호")
                        ),
                        requestParameters(
                                parameterWithName("page").description("페이지"),
                                parameterWithName("size").description("한 페이지에 표시 될 질문 정보 갯수"),
                                parameterWithName("sortBy").description("정렬 기준 ex) questionId")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data.tagId").type(JsonFieldType.NUMBER).description("태그 식별 번호").optional(),
                                        fieldWithPath("data.tagName").type(JsonFieldType.STRING).description("태그 이름").optional(),
                                        fieldWithPath("data.tagDescription").type(JsonFieldType.STRING).description("태그 설명").optional(),
                                        fieldWithPath("data.questions[]").type(JsonFieldType.ARRAY).description("질문 리스트").optional(),
                                        fieldWithPath("data.questions[].questionId").type(JsonFieldType.NUMBER).description("질문 식별 번호").optional(),
                                        fieldWithPath("data.questions[].memberId").type(JsonFieldType.NUMBER).description("회원 식별 번호"),
                                        fieldWithPath("data.questions[].questionTitle").type(JsonFieldType.STRING).description("질문 제목").optional(),
                                        fieldWithPath("data.questions[].viewCount").type(JsonFieldType.NUMBER).description("질문 조회수"),
                                        fieldWithPath("data.questions[].questionVoteSum").type(JsonFieldType.NUMBER).description("질문 투표 합계"),
                                        fieldWithPath("data.questions[].createdAt").type(JsonFieldType.STRING).description("질문 생성 일자"),
                                        fieldWithPath("data.questions[].tagName[]").type(JsonFieldType.ARRAY).description("태그 리스트").optional(),
                                        fieldWithPath("data.questions[].tagName[].tagId").type(JsonFieldType.NUMBER).description("태그 식별 번호").optional(),
                                        fieldWithPath("data.questions[].tagName[].tagName").type(JsonFieldType.STRING).description("태그 이름").optional(),
                                        fieldWithPath("data.questions[].memberName").type(JsonFieldType.STRING).description("작성 회원 이름").optional(),
                                        fieldWithPath("data.questions[].answerCount").type(JsonFieldType.NUMBER).description("").optional(),
                                        fieldWithPath("data.questions[].questionContent").type(JsonFieldType.STRING).description("질문 내용"),
                                        fieldWithPath("data.questions[].memberReputation").type(JsonFieldType.NUMBER).description("회원 명성도"),
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
    @DisplayName("Tags Get Test")
    public void getTagsTest() throws Exception {
        // given
        String page = "1";
        String size = "10";
        String sortBy = "tagId";
        String keyword = "keyword";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("page", page);
        params.add("size", size);
        params.add("sortBy", sortBy);
        params.add("keyword", keyword);

        Page<Tag> tagPage = StubData.MockTag.getMultiResultTag();
        List<TagDto.ResponseForList> tagList = StubData.MockTag.getMultiResponseBody();

        given(tagService.findTags(Mockito.anyInt(), Mockito.anyString(), Mockito.anyString())).willReturn(tagPage);
        given(tagMapper.tagsToTagsResponse(Mockito.anyList())).willReturn(tagList);

        // when
        ResultActions actions =
                mockMvc.perform(getRequestBuilder(TAG_DEFAULT_URL, params));

        // then
        actions
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("get-tags",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestParameters(
                                List.of(
                                        parameterWithName("page").description("페이지"),
                                        parameterWithName("size").description("한 페이지에 표시 될 태그 정보 갯수"),
                                        parameterWithName("sortBy").description("정렬 기준 ex) tagId"),
                                        parameterWithName("keyword").description("검색 키워드")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data[].tagId").type(JsonFieldType.NUMBER).description("태그 식별 번호").optional(),
                                        fieldWithPath("data[].tagName").type(JsonFieldType.STRING).description("태그 이름").optional(),
                                        fieldWithPath("data[].tagDescription").type(JsonFieldType.STRING).description("태그 설명").optional(),
                                        fieldWithPath("data[].questions").type(JsonFieldType.NUMBER).description("질문 리스트"),
                                        fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("페이지 정보"),
                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("현재 페이지"),
                                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("페이지 내 태그 갯수"),
                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("전체 태그 수"),
                                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("전체 페이지 수")
                                )
                        )
                ));
    }
}
