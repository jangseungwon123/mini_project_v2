package com.tenco.jobpotal.company.compSub;

import com.tenco.jobpotal.company.CompInfo;
import com.tenco.jobpotal.user.User;
import lombok.Data;

public class CompSubRequest {

    @Data
    public static class SaveDTO{
        private User user;
        private CompInfo compInfo;
        private Long userId;
        private Long companyId;
        private Long jopPostId;

        public CompSub toEntity(CompInfo loginC){
            return CompSub.builder()
                    .compInfo(loginC)
                    .user(User.builder().userId(this.userId).build())
                    .build();
        }
    }
}
