package com.tenco.jobpotal.reply.job_reply;

import lombok.Data;

public class JobCommCmtResponse {

    @Data
    public static class SaveDTO {
        private Long jobCommCmtId;
        private String content;
        private String author;
        private String createdAt;
        private Long postId;

        public SaveDTO(JobCommCmt jobCommCmt) {
            this.jobCommCmtId = jobCommCmt.getJobCommCmtId();
            this.content = jobCommCmt.getContent();
            this.author = jobCommCmt.getUser().getUserName();
            this.createdAt = jobCommCmt.getCreatedAt().toString();
            this.postId = jobCommCmt.getUserCommunity().getPostId();
        }
    }
}
