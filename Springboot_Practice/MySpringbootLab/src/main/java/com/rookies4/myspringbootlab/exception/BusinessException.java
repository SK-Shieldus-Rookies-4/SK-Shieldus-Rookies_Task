package com.rookies4.myspringbootlab.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final HttpStatus status;

    public BusinessException(String message) {
        super(message);
        this.status = HttpStatus.EXPECTATION_FAILED;
    }

    public BusinessException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    // ErrorCode 기반 생성자
    public BusinessException(ErrorCode errorCode, Object... args) {
        super(errorCode.formatMessage(args));
        this.status = errorCode.getHttpStatus();
    }
}
