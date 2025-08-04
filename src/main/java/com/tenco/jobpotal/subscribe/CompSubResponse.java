package com.tenco.jobpotal.subscribe;

import com.tenco.jobpotal.company.CompInfo;
import com.tenco.jobpotal.user.CompUser;
import com.tenco.jobpotal.user.User;
import lombok.Data;

import java.sql.Timestamp;

public class CompSubResponse {

    public static class SubListDTO{
        private Long compId;
        private CompUser compUser;
        private String companyDesc;
        private String companyCeoName;
        private String homepageUrl;
        private String phoneNumber;
        private String companyEmail;
        private String companyAddress;
        private String companyImageId;
        private String compSubDate;

        public SubListDTO(CompSub compSub) {
            User user = compSub.getUser();
            this.compId = compSub.getCompSubId();
            this.compUser = compSub.getCompInfo().getCompUser();
            this.companyDesc = compSub.getCompInfo().getCompanyDesc();
            this.companyCeoName = compSub.getCompInfo().getCompanyCeoName();
            this.homepageUrl = compSub.getCompInfo().getHomepageUrl();
            this.phoneNumber = compSub.getCompInfo().getPhoneNumber();
            this.companyEmail = compSub.getCompInfo().getCompanyEmail();
            this.companyAddress = compSub.getCompInfo().getCompanyAddress();
            this.companyImageId = compSub.getCompInfo().getCompanyImageId();
            this.compSubDate = compSub.getTime();
        }
    }


    // 구독 DTO
    @Data
    public static class SaveDTO {
        private Long compSubId;
        private Long userId;
        private Long compInfoId;

        public SaveDTO(CompSub compSub) {
          this.compSubId = compSub.getCompSubId();
          this.userId = compSub.getUser().getUserId();
          this.compInfoId = compSub.getCompInfo().getCompId();
        }
    }


}
