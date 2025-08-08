package com.tenco.jobpotal.user.applInfo;

import com.tenco.jobpotal.resume.Resume;
import lombok.Data;

public class ApplInfoResponse {


    @Data
    public static class SaveDTO{
        private Long applInfoId;
        private Long resumeId;
        private Long jopPostId;
        private String status;

        public SaveDTO(ApplInfo applInfo) {
            this.applInfoId = applInfo.getApplInfoId();
            this.resumeId = applInfo.getResume().getResumeId();
            this.jopPostId = applInfo.getJobPost().getRecruitId();
            this.status = applInfo.getStatus();
        }
    }

    // 기업이 지원 상태를 변경했을 때 반환되는 DTO
    @Data
    public static class UpdateStatusDTO{
        private Long applInfoId;
        private String status;

        public UpdateStatusDTO(ApplInfo applInfo) {
            this.applInfoId = applInfo.getApplInfoId();
            this.status = applInfo.getStatus();
        }
    }

    // [사용자]가 자신의 지원 목록을 조회할 때 사용하는 DTO
    @Data
    public static class UserApplInfoListDTO{
        private Long applInfoId;
        private Long resumeId;
        private String resumeTitle;
        private String compName;
        private Long jobPostId;
        private String jobPostTitle;
        private String createdAt;
        private String status;

        public UserApplInfoListDTO(ApplInfo applInfo) {
            this.applInfoId = applInfo.getApplInfoId();
            this.resumeId = applInfo.getResume().getResumeId();
            this.resumeTitle = applInfo.getResume().getTitle();
            this.compName = applInfo.getJobPost().getCompInfo().getCompanyName();
            this.jobPostId = applInfo.getJobPost().getRecruitId();
            this.jobPostTitle = applInfo.getJobPost().getTitle();
            this.createdAt = applInfo.getCreatedAt().toString();
            this.status = applInfo.getStatus();
        }
    }
    // [기업]지원한 지원자 목록을 조회할 때 사용하는 DTO
    @Data
    public static class CompApplInfoListDTO{
        private Long applInfoId;
        private Long jobPostId;
        private String jobPostTitle;
        private String userName;
        private Long resumeId;
        private String resumeTitle;
        private String createdAt;
        private String status;

        public CompApplInfoListDTO(ApplInfo applInfo) {
            this.applInfoId = applInfo.getApplInfoId();
            this.jobPostId = applInfo.getJobPost().getRecruitId();
            this.jobPostTitle = applInfo.getJobPost().getTitle();
            this.userName = applInfo.getResume().getUser().getUserName();
            this.resumeId = applInfo.getResume().getResumeId();
            this.resumeTitle = applInfo.getResume().getTitle();
            this.createdAt = applInfo.getCreatedAt().toString();
            this.status = applInfo.getStatus();
        }
    }
    @Data
    public static class statusDTO{
        private Long applInfoId;
        private Long resumeId;
        private Long jopPostId;
        private String status;
        boolean isApplInfoOwner;
        boolean isAccepted;
        boolean isRated;
        boolean isChoice;

        //
        public statusDTO(ApplInfo applInfo) {
            this.applInfoId = applInfo.getApplInfoId();
            this.resumeId = applInfo.getResume().getResumeId();
            this.jopPostId = applInfo.getJobPost().getRecruitId();
            this.status = applInfo.getStatus();
            this.isApplInfoOwner = applInfo.isApplInfoOwner();
            this.isAccepted = applInfo.isAccepted();
        }
    }
}

