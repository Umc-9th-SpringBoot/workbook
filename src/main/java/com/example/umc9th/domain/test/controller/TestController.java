package com.example.umc9th.domain.test.controller;

import com.example.umc9th.domain.test.exception.TestException;
import com.example.umc9th.domain.test.dto.res.TestResDTO;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/test")
    public ApiResponse<TestResDTO.Testing> test() {
        // 응답 코드 정의
        GeneralSuccessCode code = GeneralSuccessCode.OK;
        throw new TestException(GeneralErrorCode.BAD_REQUEST);
//        return ApiResponse.onSuccess(
//                code,
//                TestConverter.toTestingDTO("This is Test!")
//        );
    }
}
