package com.example.umc9th.domain.review.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "REVIEW200_1",
            "성공적으로 리뷰를 처리했습니다."),
    FOUND(HttpStatus.OK,
            "REVIEW200_2",
            "성공적으로 리뷰를 찾았습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
