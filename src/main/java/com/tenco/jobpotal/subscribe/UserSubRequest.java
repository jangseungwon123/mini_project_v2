package com.tenco.jobpotal.subscribe;

import com.tenco.jobpotal.company.CompInfo;
import com.tenco.jobpotal.user.User;
import lombok.Data;

public class UserSubRequest {

    @Data
    public static class SaveDTO {
        private Long compId;
    }
    @Data
    public static class SubListDTO {
        private Long userId;
    }

}
