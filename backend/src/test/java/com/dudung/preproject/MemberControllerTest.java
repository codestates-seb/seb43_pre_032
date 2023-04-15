package com.dudung.preproject;

import com.dudung.preproject.member.controller.MemberController;
import com.dudung.preproject.member.domain.Member;
import com.dudung.preproject.member.dto.MemberDto;
import com.dudung.preproject.member.mapper.MemberMapper;
import com.dudung.preproject.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.dudung.preproject.utils.StubData.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemberController.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureRestDocs
public class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MemberService memberService;
    @MockBean
    private MemberMapper mapper;

    @Test
    @DisplayName("Member Create Test")
    public void createMemberTest() throws Exception {
        // given
        given(mapper.memeberPostToMember(Mockito.any(MemberDto.Post.class))).willReturn(new Member());

        given(memberService.createMember(Mockito.any(Member.class))).willReturn(new Member());

        ResultActions actions =
                mockMvc.perform(
                        post("/")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(getPostMemberContent())
                );
        actions
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", is(startsWith("/"))))
                .andDo(print());
    }
    @Test
    @DisplayName("Member Patch Test")
    public void patchMemberTest() throws Exception {
        given(mapper.memberPatchToMember(Mockito.any(MemberDto.Patch.class))).willReturn(new Member());

        given(memberService.updateMember(Mockito.any(Member.class))).willReturn(new Member());

        given(mapper.memberToMemberResponse(Mockito.any(Member.class))).willReturn(getMemberResponse());

        ResultActions actions =
                mockMvc.perform(
                        patch("/{member-id}", 1)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(getPatchMemberContent())
                );
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("두둥탁"))
                .andDo(print());
    }

    @Test
    @DisplayName("Member Get Test")
    public void getMemberTest() throws Exception {
        given(memberService.findMember(Mockito.anyLong())).willReturn(new Member());

        given(mapper.memberToMemberResponse(Mockito.any(Member.class))).willReturn(getMemberResponse());

        ResultActions actions =
                mockMvc.perform(
                        get("/{member-id}", 1)
                                .accept(MediaType.APPLICATION_JSON)
                );
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("두둥탁"))
                .andDo(print());
    }
    @Test
    @DisplayName("Member Delete Test")
    public void deleteMemberTest() throws Exception {
        doNothing().when(memberService).deleteMember(Mockito.anyLong());

        mockMvc.perform(
                        delete("/{member-id}", 1)
                )
                .andExpect(status().isNoContent());
    }
}
