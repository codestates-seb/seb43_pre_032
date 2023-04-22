package com.dudung.preproject.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found"),
    MEMBER_EMAIL_EXISTS(409, "email exists"),
    MEMBER_NAME_EXISTS(409, "name exists"),
    ANSWER_NOT_FOUND(404, "Answer not found"),
    QUESTION_NOT_FOUND(404, "Question not found"),
    TAG_NOT_FOUND(404, "Tag not found"),
    ONLY_AUTHOR(403, "You don't have permission"),;

    @Getter
    private int status;
    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
