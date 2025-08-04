package com.tenco.jobpotal.company.compSub;

import com.tenco.jobpotal.company.CompInfo;
import com.tenco.jobpotal.user.CompUser;
import com.tenco.jobpotal.user.User;
import lombok.Builder;
import lombok.Data;

public class CompSubResponse {

    // 구독 DTO
    @Data
    public static class SaveDTO {
        private Long id;
        private User user;
        private CompUser compUser;

        public SaveDTO(CompSub compSub) {
            this.id = compSub.getId();
            this.user = User.builder().userId(compSub.getUser().getUserId())
                    .build();
            this.compUser = CompUser.builder().compUserId(compSub.getCompUser().getCompUserId())
                    .build();
        }
    }


}
