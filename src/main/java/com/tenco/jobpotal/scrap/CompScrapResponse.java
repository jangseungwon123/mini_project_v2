package com.tenco.jobpotal.scrap;

import com.tenco.jobpotal.company.CompInfo;
import lombok.Data;

public class CompScrapResponse {

    @Data
    public static class SubListDTO {
        private Long userSubId;
        private Long compId;
        private String companyName;
        private String companyDesc;
        private String companyCeoName;
        private String homepageUrl;
        private String phoneNumber;
        private String companyEmail;
        private String companyAddress;
        private String companyImageId;
        private String userSubDate;

        public SubListDTO(CompScrap compScrap) {
            CompInfo compInfo = compScrap.getCompInfo();
            this.userSubId = compScrap.getUserSubId();
            this.compId = compInfo.getCompId();
            this.companyName = compInfo.getCompanyName();
            this.companyDesc = compInfo.getCompanyDesc();
            this.companyCeoName = compInfo.getCompanyCeoName();
            this.homepageUrl = compInfo.getHomepageUrl();
            this.phoneNumber = compInfo.getPhoneNumber();
            this.companyEmail = compInfo.getCompanyEmail();
            this.companyAddress = compInfo.getCompanyAddress();
            this.companyImageId = compInfo.getCompanyImageId();
            this.userSubDate = compScrap.getTime();
        }
    }

    @Data
    public static class SaveDTO {
        private Long userSubId;
        private Long userId;
        private Long compInfoId;

        public SaveDTO(CompScrap compScrap) {
            this.userSubId = compScrap.getUserSubId();
            this.userId = compScrap.getUser().getUserId();
            this.compInfoId = compScrap.getCompInfo().getCompId();
        }
    }
}
