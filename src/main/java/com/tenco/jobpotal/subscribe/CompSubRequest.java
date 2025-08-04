package com.tenco.jobpotal.subscribe;

import com.tenco.jobpotal.user.CompUser;
import com.tenco.jobpotal.user.User;
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
