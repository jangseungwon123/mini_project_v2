package com.tenco.jobpotal.scrap;

import lombok.Data;

public class UserScrapRequest {

    @Data
    public static class SaveDTO {
        private Long recruitId;
    }
    @Data
    public static class ScrapListDTO {
        private Long userId;
    }

}
