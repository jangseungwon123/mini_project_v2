package com.tenco.jobpotal.company;



import com.tenco.jobpotal.user.comp.CompUser;
import lombok.Data;

public class CompInfoRequest {

    @Data
    public static class SaveDTO {
        private Long companyId;
        private CompUser compUser;
        private String companyName;
        private String companyDesc;
        private String companyCeoName;
        private String homepageUrl;
        private String phoneNumber;
        private String companyEmail;
        private String companyAddress;

        public CompInfo toEntity(CompUser compUser) {
            return CompInfo.builder()
                    .compId(companyId)
                    .compUser(compUser)
                    .companyName(companyName)
                    .companyDesc(companyDesc)
                    .companyCeoName(companyCeoName)
                    .homepageUrl(homepageUrl)
                    .phoneNumber(phoneNumber)
                    .companyEmail(companyEmail)
                    .companyAddress(companyAddress)
                    .instId(compUser.getCompUserLoginId())
                    .build();
        }
    }

    @Data
    public static class UpdateDTO {
        private Long companyId;
        private CompUser compUser;
        private String companyName;
        private String companyDesc;
        private String companyCeoName;
        private String homepageUrl;
        private String phoneNumber;
        private String companyEmail;
        private String companyAddress;
        private String companyImageId;

        public CompInfo toEntity(CompUser compUser) {
            return CompInfo.builder()
                    .compId(companyId)
                    .compUser(compUser)
                    .companyName(companyName)
                    .companyDesc(companyDesc)
                    .companyCeoName(companyCeoName)
                    .homepageUrl(homepageUrl)
                    .phoneNumber(phoneNumber)
                    .companyEmail(companyEmail)
                    .companyAddress(companyAddress)
                    .companyImageId(companyImageId)
                    .instId(compUser.getCompUserLoginId())
                    .build();
        }
    }
    /*
    @Data
    public static class SaveReviewDTO {

        private Long reviewId;
        private String content;
        private boolean isCurrentEmployee;
        private boolean isRecommended;
        
        // selectBox 값 String -> boolean 변환을 위한 변수
        private String isCurrentEmployeeYn;
        private String isRecommendedYn;

        public CompanyReview toEntity(User user, CompanyInfo companyInfo) {

            return CompanyReview.builder()
                    .content(content)
                    .user(user)
                    .companyInfo(companyInfo)
                    .isCurrentEmployee(isCurrentEmployee)
                    .isRecommended(isRecommended)
                    .build();
        }
    }

     */
}
