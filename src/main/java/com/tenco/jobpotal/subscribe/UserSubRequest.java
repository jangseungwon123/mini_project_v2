package com.tenco.jobpotal.subscribe;

import lombok.Data;

public class UserSubRequest {

    @Data
    public static class SaveDTO {
        private Long compInfoId;
    }
    @Data
    public static class SubListDTO {
        private Long userId;
    }

}
