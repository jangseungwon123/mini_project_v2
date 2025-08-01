package com.tenco.jobpotal.resume;

import lombok.Data;

public class ResumeRequest {

    // 이력서 저장 DTO
    @Data
    public static class SaveDTO {


        private String title;
        private String content;

    }


    // 이력서 수정 DTO
}
