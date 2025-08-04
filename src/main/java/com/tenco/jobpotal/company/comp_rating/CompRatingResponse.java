package com.tenco.jobpotal.company.comp_rating;

import com.tenco.jobpotal.user.appl_info.AppiInfo;

public class CompRatingResponse {

    public static class SaveDTO{
        private Long ratingId;
        private AppiInfo applInfoId;
        private int score;

        public SaveDTO(AppiInfo appiInfo) {
            this.ratingId = ratingId;
            this.applInfoId = applInfoId;
            this.score = score;
        }
    }



}
