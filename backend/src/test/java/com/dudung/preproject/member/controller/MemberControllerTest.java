package com.dudung.preproject.member.controller;

import com.dudung.preproject.auth.jwt.JwtTokenizer;
import com.dudung.preproject.helper.MemberControllerHelper;
import com.dudung.preproject.helper.StubData;
import com.dudung.preproject.member.domain.Member;
import com.dudung.preproject.member.dto.MemberDto;
import com.dudung.preproject.member.mapper.MemberMapper;
import com.dudung.preproject.member.service.MemberService;
import com.dudung.preproject.question.domain.Question;
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
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.lang.annotation.Documented;
import java.util.List;

import static com.dudung.preproject.helper.MemberControllerHelper.MEMBER_RESOURCE_URI;
import static com.dudung.preproject.helper.StubData.*;
import static com.dudung.preproject.helper.StubData.MockMyPage.getMyPageResponse;
import static com.dudung.preproject.utils.ApiDocumentUtils.getRequestPreProcessor;
import static com.dudung.preproject.utils.ApiDocumentUtils.getResponsePreProcessor;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemberController.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureRestDocs
@AutoConfigureMockMvc(addFilters = false)
@MockBean(JpaMetamodelMappingContext.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MemberControllerTest implements MemberControllerHelper {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JwtTokenizer jwtTokenizer;
    @MockBean
    private MemberService memberService;
    @MockBean
    private MemberMapper mapper;

    private  String accessToken;

    @BeforeAll
    public void init() {
        accessToken = StubData.MockSecurity.getValidAccessToken(jwtTokenizer.getSecretKey());
    }

    @Test
    @DisplayName("Member Create Test")
    public void createMemberTest() throws Exception {
        // given
        given(mapper.memeberPostToMember(Mockito.any(MemberDto.Post.class))).willReturn(new Member());

        given(memberService.createMember(Mockito.any(Member.class))).willReturn(new Member());

        ResultActions actions =
                mockMvc.perform(
                        post("/members")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(getPostMemberContent())
                );
        actions
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", is(startsWith("/members"))))
                .andDo(print())
                .andDo(document("post-member",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestFields(
                                List.of(
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("회원 이메일"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("회원 비밀번호"),
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("회원 이름")
                                )
                        )
                ));
    }
    @Test
    @DisplayName("Members Get Test")
    public void getMembersTest() throws Exception {
        String page = "1";
        String size = "10";
        String sortBy = "memberId";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("page", page);
        params.add("size", size);
        params.add("sortBy", sortBy);

        Page<Member> members = StubData.MockMember.getMultiResultMember();
        List<MemberDto.ResponseForList> memberList = StubData.MockMember.getMemberList();

        given(memberService.findMembers(Mockito.anyInt(), Mockito.anyInt())).willReturn(members);
        given(mapper.membersToMemberResponses(Mockito.anyList())).willReturn(memberList);

        ResultActions actions =
                mockMvc.perform(
                        get("/members")
                                .accept(MediaType.APPLICATION_JSON)
                                .params(params));

        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andDo(print())
                .andDo(document("get-members",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestParameters(
                                List.of(
                                        parameterWithName("page").description("페이지"),
                                        parameterWithName("size").description("한 페이지 내 표시 될 회원 수"),
                                        parameterWithName("sortBy").description("정렬기준")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data[]").type(JsonFieldType.ARRAY).description("회원 정보 리스트"),
                                        fieldWithPath("data[].memberId").type(JsonFieldType.NUMBER).description("회원 식별 번호"),
                                        fieldWithPath("data[].name").type(JsonFieldType.STRING).description("회원명"),
                                        fieldWithPath("data[].reputation").type(JsonFieldType.NUMBER).description("명성도"),
                                        fieldWithPath("pageInfo").type(JsonFieldType.OBJECT).description("페이지 정보"),
                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("현재 페이지"),
                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("전체 회원 수"),
                                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("전체 페이지 수")
                                )
                        )
                ));
    }

    @Test
    @DisplayName("Member Delete Test")
    public void deleteMemberTest() throws Exception {
        doNothing().when(memberService).deleteMember(anyLong(), anyLong());

        mockMvc.perform(deleteRequestBuilder(MEMBER_RESOURCE_URI, 1L, accessToken))
                .andExpect(status().isNoContent())
                .andDo(document("delete-member",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestHeaders(
                                getDefaultRequestHeaderDescriptor()
                        ),
                        pathParameters(
                                        parameterWithName("member-id").description("회원 식별 번호")
                        )
                ));
    }

    @Test
    @DisplayName("MyPage Patch Test")
    public void patchMyPageTest() throws Exception {

        MemberDto.MyPagePatch patch = (MemberDto.MyPagePatch) StubData.MockMyPage.getRequestBody(HttpMethod.PATCH);
        String content = toJsonContent(patch);

        given(mapper.responserMypagePatchToMember(Mockito.any(MemberDto.MyPagePatch.class))).willReturn(new Member());

        given(memberService.updateMyPage(Mockito.any(Member.class), Mockito.anyLong())).willReturn(new Member());

        given(mapper.memberToMyPage(Mockito.any(Member.class))).willReturn(StubData.MockMyPage.getMyPageResponse());

        ResultActions actions =
                mockMvc.perform(patchRequestBuilder(MYPAGE_RESOURCE_URI, 1L, content, accessToken));

        actions
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("patch-mypage",
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
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별 번호"),                                        
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("회원 이름"),
                                        fieldWithPath("myPageTitle").type(JsonFieldType.STRING).description("마이페이지 Title"),
                                        fieldWithPath("aboutMe").type(JsonFieldType.STRING).description("마이페이지 AboutMe")
                                        )
                        )
                ));
    }



    @Test
    @DisplayName("MyPage Get Test")
    public void getMyPageTest() throws Exception {

        given(memberService.findMember(Mockito.anyLong())).willReturn(new Member());

        given(mapper.memberToMyPage(Mockito.any(Member.class))).willReturn(StubData.MockMyPage.getMyPageResponse());

        ResultActions actions =
                mockMvc.perform(getRequestBuilder(MYPAGE_RESOURCE_URI, 1L));

        actions
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("get-mypage",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        pathParameters(
                                getMemberRequestPathParameterDescriptor()
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별 번호"),
                                        fieldWithPath("memberJpegUrl").type(JsonFieldType.STRING).description("회원 이미지.JPG 주소"),
                                        fieldWithPath("memberPngUrl").type(JsonFieldType.STRING).description("회원 이미지.PNG 주소"),
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("회원 이름"),
                                        fieldWithPath("myPageTitle").type(JsonFieldType.STRING).description("마이페이지 Title"),
                                        fieldWithPath("aboutMe").type(JsonFieldType.STRING).description("마이페이지 AboutMe"),
                                        fieldWithPath("createAt").type(JsonFieldType.STRING).description("마이페이지 작성 시간"),
                                        fieldWithPath("modifiedAt").type(JsonFieldType.STRING).description("마이페이지 수정 시간"),
                                        fieldWithPath("reputation").type(JsonFieldType.NUMBER).description("회원 명성도"),
                                        fieldWithPath("questionCount").type(JsonFieldType.NUMBER).description("질문 개수"),
                                        fieldWithPath("answerCount").type(JsonFieldType.NUMBER).description("답변 개수"),
                                        fieldWithPath("questions[].questionId").type(JsonFieldType.NUMBER).description("질문 식별 번호"),
                                        fieldWithPath("questions[].questionsTitle").type(JsonFieldType.STRING).description("질문 제목"),
                                        fieldWithPath("answers[].questionId").type(JsonFieldType.NUMBER).description("질문 식별 번호"),
                                        fieldWithPath("answers[].answerId").type(JsonFieldType.NUMBER).description("답변 식별 번호"),
                                        fieldWithPath("answers[].answerContent").type(JsonFieldType.STRING).description("질문 내용")
                                )
                        )
                ));
    }
}
