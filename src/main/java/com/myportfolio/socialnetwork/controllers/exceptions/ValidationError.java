package com.myportfolio.socialnetwork.controllers.exceptions;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    private static final long serialVersionUID = 1L;

    @Getter
    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
    }

    public void addError(String fieldName, String message) {
        this.errors.add(new FieldMessage(fieldName, message));
    }
}
