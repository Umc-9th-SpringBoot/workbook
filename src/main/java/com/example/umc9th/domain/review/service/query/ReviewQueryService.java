package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.entity.QStore;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    // 검색 API
    public List<Review> searchReview(
            String filter,
            String type
    ) throws Exception {

        // Q클래스 정의
        QReview review = QReview.review;
        QStore store = QStore.store;

        // BooleanBuilder 정의
        BooleanBuilder builder = new BooleanBuilder();

        // 동적 쿼리: 검색 조건
        List<Review> reviewList;
        if (type.equals("store")) {

            // 가게별 조건 생성
            builder.and(review.store.name.eq(filter));
        }
        if (type.equals("star")) {

            // 별점별 조건 생성: 정수만 추출
            switch ((int) Float.parseFloat(filter)) {
                case 5 -> builder.and(review.star.between(5.0F, 5.0F));
                case 4 -> builder.and(review.star.between(4.0F, 4.9F));
                case 3 -> builder.and(review.star.between(3.0F, 3.9F));
                case 2 -> builder.and(review.star.between(2.0F, 2.9F));
                case 1 -> builder.and(review.star.between(1.0F, 1.9F));
                default -> builder.and(review.star.between(0.0F, 0.9F));
            }
        }

        // 쿼리문 전송
        reviewList = reviewRepository.findMyReview(builder);

        // 예외 조건: 예외 핸들러 X
        if (reviewList.isEmpty()) {
            throw new Exception("검색 결과가 없습니다.");
        }

        // 리턴
        return reviewList;
    }
}
