//package com.tenco.jobpotal.company.comp_rating;
//
//import com.tenco.jobpotal._core.errors.exception.Exception400;
//import com.tenco.jobpotal.user.normal.User;
//import com.tenco.jobpotal.user.appl_info.AppiInfo;
//import lombok.Data;
//
//public class CompRatingRequest  {
//
//    @Data
//    public static class SaveDTO{
//        private AppiInfo applInfoId;
//        private int score;
//
//        public void validate(){
//            if (applInfoId == null){
//                throw new Exception400("회사의 정보가 없습니다.");
//            }
//        }
//
//    }
//}
