package com.example.umc9th.domain.review.service.query;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.example.umc9th.domain.store.StoreErrorCode;
import com.example.umc9th.domain.store.StoreException;
import com.example.umc9th.domain.store.entity.QStore;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.repository.StoreRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    // 검색 API
    @Override
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

    @Override
    public ReviewResDTO.ReviewPreViewListDTO findReview(
            String storeName,
            Integer page
    ){
        // - 가게를 가져온다 (가게 존재 여부 검증)
        Store store = storeRepository.findByName(storeName)
                //    - 없으면 예외 터뜨린다
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        //- 가게에 맞는 리뷰를 가져온다 (Offset 페이징)
        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<Review> result = reviewRepository.findAllByStore(store, pageRequest);

        //- 결과를 응답 DTO로 변환한다 (컨버터 이용)
        return ReviewConverter.toReviewPreviewListDTO(result);
    }
}
