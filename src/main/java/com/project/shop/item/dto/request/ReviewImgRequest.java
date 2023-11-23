package com.project.shop.item.dto.request;


import com.project.shop.item.domain.Review;
import com.project.shop.item.domain.ReviewImg;

import javax.validation.constraints.NotBlank;

public record ReviewImgRequest(
        @NotBlank Review review,
        @NotBlank String imgUrl

        ) {
        public ReviewImg toEntity(){
                return ReviewImg.builder()
                        .review(this.review)
                        .imgUrl(this.imgUrl)
                        .build();
        }


}
