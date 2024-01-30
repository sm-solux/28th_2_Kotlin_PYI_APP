package com.solux.pyi.pyiplanyouridea.review.dto;

import com.solux.pyi.pyiplanyouridea.review.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

    private Long reviewUuid;
    private String reviewTitle;
    private String reviewDetails;
    private LocalDateTime reviewCreated;

    public ReviewDto(Review entity) {
        this.reviewUuid = entity.getReviewUuid();
        this.reviewTitle = entity.getReviewTitle();
        this.reviewDetails = entity.getReviewDetails();
        this.reviewCreated = entity.getReviewCreated();
    }

}
