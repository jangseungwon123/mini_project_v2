package com.tenco.jobpotal.user.normal;

import lombok.Builder;
import lombok.Data;

public class UserResponse {

    // 회원가입 후 응답 DTO
    @Data
    public static class JoinDTO {
        private String userName;
        private String userLoginId;
        private String userEmail;
        private String userAddress;
        private String userPhone;
        private String userBirth;
        private String userGender;
        private String userNickname;

        @Builder
        public JoinDTO(User user) {
            this.userName = user.getUserName();
            this.userLoginId = user.getUserLoginId();
            this.userEmail = user.getUserEmail();
            this.userAddress = user.getUserAddress();
            this.userPhone = user.getUserPhone();
            this.userBirth = user.getUserBirth();
            this.userGender = user.getUserGender();
            this.userNickname = user.getUserNickname();
        }
    }

    // 로그인 후 응답 DTO
    @Data
    public static class LoginDTO {
        private String userLoginId;

        @Builder
        public LoginDTO(User user) {
            this.userLoginId = user.getUserLoginId();
        }
    }

    // 회원 정보 수정 후 응답 DTO
    @Data
    public static class  UpdateDTO {
        private String userName;
        private String userEmail;
        private String userAddress;
        private String userPhone;
        private String userNickname;

        @Builder
        public UpdateDTO(User user) {
            this.userName = user.getUserName();
            this.userEmail = user.getUserEmail();
            this.userAddress = user.getUserAddress();
            this.userPhone = user.getUserPhone();
            this.userNickname = user.getUserNickname();
        }
    }

    // 회원정보 조회 응답 DTO
    @Data
    public static class DetailDTO {
        private String userName;
        private String userLoginId;
        private String userEmail;
        private String userAddress;
        private String userPhone;
        private String userBirth;
        private String userGender;
        private String userNickname;

        @Builder
        public DetailDTO(User user) {
            this.userName = user.getUserName();
            this.userLoginId = user.getUserLoginId();
            this.userEmail = user.getUserEmail();
            this.userAddress = user.getUserAddress();
            this.userPhone = user.getUserPhone();
            this.userBirth = user.getUserBirth();
            this.userGender = user.getUserGender();
            this.userNickname = user.getUserNickname();
        }
    }

}
