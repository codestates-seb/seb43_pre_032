package com.dudung.preproject.helper;

import org.springframework.restdocs.request.ParameterDescriptor;

import java.util.Arrays;
import java.util.List;

import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

public interface AnswerAnswerControllerHelper extends ControllerHelper{
    String ANSWER_ANSWER_DEFAULT_URL = "/answeranswers";
    String ANSWER_ANSWER_RESOURCE_ID = "/{answeranswer-id}";
    String ANSWER_ANSWER_RESOURCE_URI = ANSWER_ANSWER_DEFAULT_URL + ANSWER_ANSWER_RESOURCE_ID;

    default List<ParameterDescriptor> getMemberRequestPathParameterDescriptor() {
        return Arrays.asList(parameterWithName("answeranswer-id").description("답변 댓글 식별 번호"));
    }
}