package com.tenco.jobpotal.resume;

import lombok.Data;

public class ResumeResponse {

    @Data
    public static class SaveDTO{
        private String title;
        private String content;
        private String phone;
        private String address;
        private String birth;
        private String email;
        private String gender;
        private char isExperienced;
        private char isShow;

        public SaveDTO(Resume resume) {
            this.title = resume.getTitle();
            this.content = resume.getContent();
            this.phone = resume.getPhone();
            this.address = resume.getAddress();
            this.birth = resume.getBirth();
            this.email = resume.getEmail();
            this.gender = resume.getGender();
            this.isExperienced = resume.getIsExperienced();
            this.isShow = resume.getIsShow();
        }
    }

    @Data
    public static class UpdateDTO{
        private String title;
        private String content;
        private String phone;
        private String address;
        private String birth;
        private String email;
        private String gender;
        private char isExperienced;
        private char isShow;

        public UpdateDTO(Resume resume) {
            this.title = resume.getTitle();
            this.content = resume.getContent();
            this.phone = resume.getPhone();
            this.address = resume.getAddress();
            this.birth = resume.getBirth();
            this.email = resume.getEmail();
            this.gender = resume.getGender();
            this.isExperienced = resume.getIsExperienced();
            this.isShow = resume.getIsShow();
        }
    }
}
