package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import com.querydsl.core.BooleanBuilder;

import java.util.List;

public interface ReviewQueryDsl {

    // 내가 작성한 리뷰 보기 API
    List<Review> findMyReview(
            BooleanBuilder predicate
    );
}
