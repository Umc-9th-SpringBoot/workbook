package com.example.umc9th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode{

    OK(HttpStatus.OK,
            "COMMON200",
            "성공적으로 요청을 처리했습니다."),
    CREATED(HttpStatus.CREATED,
            "COMMON201",
            "성공적으로 요청을 생성했습니다."),
    NO_CONTENT(HttpStatus.NO_CONTENT,
            "COMMON204",
            "제공할 컨텐츠가 없습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
