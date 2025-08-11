package com.tenco.jobpotal.company.compRating;

import com.tenco.jobpotal.user.applInfo.ApplInfo;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CompRatingResponse {

    public static class SaveDTO{
        private Long ratingId;
        private Long applInfo;
        private int score;
        public SaveDTO(CompRating compRating) {
            this.ratingId = compRating.getRatingId();
            this.applInfo = compRating.getApplInfo().getApplInfoId();
            this.score = compRating.getScore();
        }
    }

    public static class UpdateDTO{
        private int score;
        public UpdateDTO(CompRating compRating) {
            this.score = compRating.getScore();
        }
    }



}
