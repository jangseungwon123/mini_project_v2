package com.tenco.jobpotal.user.adminInfo;

import lombok.Data;

public class AdminInfoResponse {
    // 회원가입 후 응답 DTO
    @Data
    public static class JoinDTO {
        private String adminLoginId;
        private String adminPassword;
        private String adminName;
        private String adminEmail;
        private String adminPhone;

        public JoinDTO(AdminInfo adminInfo) {
            this.adminLoginId = adminInfo.getAdminLoginId();
            this.adminPassword = adminInfo.getAdminPassword();
            this.adminName = adminInfo.getAdminName();
            this.adminEmail = adminInfo.getAdminEmail();
            this.adminPhone = adminInfo.getAdminPhone();
        }
    }

    // 로그인 후 응답 DTO
    @Data
    public static class LoginDTO {
        private String adminLoginId;

        public LoginDTO(AdminInfo adminInfo) {
            this.adminLoginId = adminInfo.getAdminLoginId();
        }
    }

    // 회원 정보 수정 후 응답 DTO
    @Data
    public static class UpdateDTO {
        private String adminName;
        private String adminEmail;
        private String adminPhone;

        public UpdateDTO(AdminInfo adminInfo) {
            this.adminName = adminInfo.getAdminName();
            this.adminEmail = adminInfo.getAdminEmail();
            this.adminPhone = adminInfo.getAdminPhone();
        }
    }

    // 회원정보 조회 응답 DTO
    @Data
    public static class DetailDTO {
        private String adminLoginId;
        private String adminName;
        private String adminEmail;
        private String adminPhone;

        public DetailDTO(AdminInfo adminInfo) {
            this.adminLoginId = adminLoginId;
            this.adminName = adminName;
            this.adminEmail = adminEmail;
            this.adminPhone = adminPhone;
        }
    }

}
