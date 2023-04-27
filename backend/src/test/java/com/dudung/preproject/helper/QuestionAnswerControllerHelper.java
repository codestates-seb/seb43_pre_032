package com.dudung.preproject.helper;

import org.springframework.restdocs.request.ParameterDescriptor;

import java.util.Arrays;
import java.util.List;

import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

public interface QuestionAnswerControllerHelper extends ControllerHelper {
    String QUESTION_ANSWER_DEFAULT_URL = "/questionanswers";
    String QUESTION_ANSWER_RESOURCE_ID = "/{questionanswer-id}";
    String QUESTION_ANSWER_RESOURCE_URI = QUESTION_ANSWER_DEFAULT_URL + QUESTION_ANSWER_RESOURCE_ID;
    default List<ParameterDescriptor> getMemberRequestPathParameterDescriptor() {
        return Arrays.asList(parameterWithName("questionanswer-id").description("질문 댓글 식별 번호"));
    }
}
