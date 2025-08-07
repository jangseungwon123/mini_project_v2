package com.tenco.jobpotal.company;



import com.tenco.jobpotal.user.LoginUser;
import com.tenco.jobpotal.user.comp.CompUser;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

public class CompInfoRequest {

    @Data
    public static class SaveDTO {
        @NotEmpty(message = "회사명을 입력 해주세요")
        private String companyName;
        @NotEmpty(message = "회사 설명을 입력 해주세요")
        private String companyDesc;
        @NotEmpty(message = "CEO 이름을 입력 해주세요")
        private String companyCeoName;
        private String homepageUrl;
        @NotEmpty(message = "회사 전화번호를 입력 해주세요")
        @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "전화번호 형식으로 작성해주세요")
        private String phoneNumber;
        @NotEmpty(message = "회사 이메일을 입력 해주세요")
        @Pattern(regexp = "^[a-zA-Z0-9]{2,10}@[a-zA-Z0-9]{2,6}\\.[a-zA-Z]{2,3}$",
                message = "이메일 형식으로 작성해주세요")
        private String companyEmail;
        @NotEmpty(message = "회사 주소를 입력해주세요")
        private String companyAddress;

        public CompInfo toEntity(LoginUser loginUser, CompUser compUser) {
            return CompInfo.builder()
                    .compUser(compUser)
                    .companyName(companyName)
                    .companyDesc(companyDesc)
                    .companyCeoName(companyCeoName)
                    .homepageUrl(homepageUrl)
                    .phoneNumber(phoneNumber)
                    .companyEmail(companyEmail)
                    .companyAddress(companyAddress)
                    .instId(loginUser.getLoginId())
                    .build();
        }
    }

    @Data
    public static class UpdateDTO {
        @NotNull(message = "회사 ID가 없습니다")
        private Long companyId;
        @NotEmpty(message = "회사명을 입력 해주세요")
        private String companyName;
        @NotEmpty(message = "회사 설명을 입력 해주세요")
        private String companyDesc;
        @NotEmpty(message = "CEO 이름을 입력 해주세요")
        private String companyCeoName;
        private String homepageUrl;
        @NotEmpty(message = "회사 전화번호를 입력 해주세요")
        @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "전화번호 형식으로 작성해주세요")
        private String phoneNumber;
        @NotEmpty(message = "회사 이메일을 입력 해주세요")
        @Pattern(regexp = "^[a-zA-Z0-9]{2,10}@[a-zA-Z0-9]{2,6}\\.[a-zA-Z]{2,3}$",
                message = "이메일 형식으로 작성해주세요")
        private String companyEmail;
        @NotEmpty(message = "회사 주소를 입력해주세요")
        private String companyAddress;

        public CompInfo toEntity(LoginUser loginUser, CompUser compUser) {
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
                    .instId(loginUser.getLoginId())
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
