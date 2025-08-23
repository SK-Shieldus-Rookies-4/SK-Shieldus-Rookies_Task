package com.rookies4.myspringbootlab.exception.advice;

import com.rookies4.myspringbootlab.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /** 비즈니스 예외 처리 */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
        log.error("BusinessException: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(ex.getHttpStatus().value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }

    /** DTO 유효성 검증 실패 */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {

        // 필드명 -> 에러 메시지
        Map<String, String> errorMap = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage,
                        (existing, replacement) -> existing
                ));

        ValidationErrorResponse response = new ValidationErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Validation Failed",
                errorMap
        );

        log.error("ValidationException: {}", errorMap);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}