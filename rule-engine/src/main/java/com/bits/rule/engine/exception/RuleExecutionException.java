package com.bits.rule.engine.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class RuleExecutionException extends RuntimeException {
    public RuleExecutionException(String message) {
        super(message);
    }
}
