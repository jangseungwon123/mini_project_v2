package com.tenco.jobpotal.reply.job_reply;

import com.tenco.jobpotal.community.community1.Community;
import com.tenco.jobpotal.user.normal.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class JobCommCmtRequest {

    @Data
    public static class SaveDTO {
        @NotNull(message = "게시물 ID가 필요합니다.")
        private Long postId;

        @NotBlank(message = "내용을 입력 해주세요.")
        @Size(min = 20, max = 100, message = "내용을 최소 20자 최대 100자로 입력 해주세요.")
        private String content;

        public JobCommCmt toEntity(User loginUser, Community community) {
            return JobCommCmt.builder()
                    .content(content.trim())
                    .user(loginUser)
                    .community(community)
                    .build();
        }
    }

}
