package com.example.umc9th.global.apiPayload.handler;

import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GeneralExceptionAdvice {

    // 애플리케이션에서 발생하는 커스텀 예외를 처리
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<Void>> handleCustomException(
            GeneralException ex
    ) {
        //예외가 발생하면 로그 기록
        log.warn("[ GeneralException ]: {}", ex.getCode().getMessage());
        //커스텀 예외에 정의된 에러 코드와 메시지를 포함한 응답 제공
        return ResponseEntity.status(ex.getCode().getStatus())
                .body(ApiResponse.onFailure(
                                ex.getCode(),
                                null
                        )
                );
    }

    // 그 외의 정의되지 않은 모든 예외 처리
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiResponse<String>> handleAllException(
            Exception ex
    ) {
        log.error("[WARNING] Internal Server Error : {} ", ex.getMessage());
        BaseErrorCode errorCode = GeneralErrorCode.BAD_REQUEST;
        ApiResponse<String> errorResponse = ApiResponse.onFailure(
                errorCode,
                null
        );
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(errorResponse);
    }
}
