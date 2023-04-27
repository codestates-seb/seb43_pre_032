package com.dudung.preproject.helper;

import com.dudung.preproject.answer.domain.Answer;
import org.springframework.restdocs.request.ParameterDescriptor;

import java.util.Arrays;
import java.util.List;

import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

public interface AnswerControllerHelper extends ControllerHelper{

    String ANSWER_DEFAULT_URL = "/answers";
    String ANSWER_RESOURCE_ID = "/{answer-id}";
    String ANSWER_RESOURCE_URI = ANSWER_DEFAULT_URL + ANSWER_RESOURCE_ID;

    default List<ParameterDescriptor> getMemberRequestPathParameterDescriptor() {
        return Arrays.asList(parameterWithName("answer-id").description("답변 식별 번호"));
    }



}
