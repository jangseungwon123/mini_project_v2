package com.tenco.jobpotal.company;

import lombok.Builder;
import lombok.Data;

public class CompInfoResponse {
    // 기업 목록 응답 DTO
    @Data
    public static class MainDTO {
        private Long compId;
        private String companyName;
        private String companyDesc;
        private String companyCeoName;
        private String homepageUrl;
        private String phoneNumber;
        private String companyEmail;
        private String companyAddress;
        private String companyImageId;

        @Builder
        public MainDTO(CompInfo compInfo) {
            this.compId = compInfo.getCompId();
            this.companyName = compInfo.getCompanyName();
            this.companyDesc = compInfo.getCompanyDesc();
            this.companyCeoName = compInfo.getCompanyCeoName();
            this.homepageUrl = compInfo.getHomepageUrl();
            this.phoneNumber = compInfo.getPhoneNumber();
            this.companyEmail = compInfo.getCompanyEmail();
            this.companyAddress = compInfo.getCompanyAddress();
            this.companyImageId = compInfo.getCompanyImageId();
        }
    } // END OF INNER CLASS

    // 게시글 상세보기 응답 DTO 설계
}
