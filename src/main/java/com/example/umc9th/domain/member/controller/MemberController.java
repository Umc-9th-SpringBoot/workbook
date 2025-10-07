package com.example.umc9th.domain.member.controller;

import com.example.umc9th.domain.member.service.query.MemberQueryService;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberQueryService memberQueryService;
    private final ReviewRepository reviewRepository;

    @GetMapping("/query-test")
    public List<Review> queryTest(
            @RequestParam String name
    ){
        List<Review> result = reviewRepository.searchReviewByLocation(name);
        return result;
    }
}
