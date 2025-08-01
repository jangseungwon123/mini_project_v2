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
        private char gender;

        public SaveDTO(Resume resume) {
            this.title = resume.getTitle();
            this.content = resume.getContent();
            this.phone = resume.getPhone();
            this.address = resume.getAddress();
            this.birth = resume.getBirth();
            this.email = resume.getEmail();
            this.gender = resume.getGender();
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
        private char gender;

        public UpdateDTO(Resume resume) {
            this.title = resume.getTitle();
            this.content = resume.getContent();
            this.phone = resume.getPhone();
            this.address = resume.getAddress();
            this.birth = resume.getBirth();
            this.email = resume.getEmail();
            this.gender = resume.getGender();
        }
    }
}
