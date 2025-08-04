package com.tenco.jobpotal.subscribe;

import lombok.Data;

public class CompSubRequest {

    @Data
    public static class SaveDTO {
        private Long userId;
    }
    public static class SubListDTO{
        private Long compInfoId;
    }

}
