package com.tenco.jobpotal.company.compSub;

import com.tenco.jobpotal.company.CompInfo;
import com.tenco.jobpotal.user.User;
import lombok.Builder;
import lombok.Data;

public class CompSubResponse {

    // 구독 DTO
    @Data
    public static class SaveDTO {
        private Long id;
        private User user;
        private CompInfo compInfo;

        public SaveDTO(CompSub compSub) {
            this.id = compSub.getId();
            this.user = User.builder().userId(compSub.getUser().getUserId())
                    .build();
            this.compInfo = CompInfo.builder().compId(compSub.getCompInfo().getCompId())
                    .build();
        }
    }


}
