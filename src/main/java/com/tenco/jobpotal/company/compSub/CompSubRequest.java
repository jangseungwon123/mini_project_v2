package com.tenco.jobpotal.company.compSub;

import com.tenco.jobpotal.user.comp.CompUser;
import com.tenco.jobpotal.user.normal.User;
import lombok.Data;

public class CompSubRequest {

    @Data
    public static class SaveDTO{
        private User user;
        private CompUser compUser;
        private Long userId;
//        private Long companyId;
//        private Long jopPostId;

        public CompSub toEntity(CompUser loginC){
            return CompSub.builder()
                    .compUser(loginC)
                    .user(User.builder()
                            .userId(user.getUserId())
                            .build())
                    .build();
        }
    }
}
