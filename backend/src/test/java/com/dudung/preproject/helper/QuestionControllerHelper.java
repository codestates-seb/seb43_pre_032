package com.dudung.preproject.helper;

import org.springframework.restdocs.request.ParameterDescriptor;

import java.util.Arrays;
import java.util.List;

import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

public interface QuestionControllerHelper extends ControllerHelper {
    String QUESTION_DEFAULT_URL = "/questions";
    String QUESTION_RESOURCE_ID = "/{question-id}";
    String QUESTION_RESOURCE_URI = QUESTION_DEFAULT_URL + QUESTION_RESOURCE_ID;

    default List<ParameterDescriptor> getMemberRequestPathParameterDescriptor() {
        return Arrays.asList(parameterWithName("question-id").description("질문 식별 번호"));
    }
}
