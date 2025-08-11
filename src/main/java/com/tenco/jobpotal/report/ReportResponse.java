package com.tenco.jobpotal.report;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

public class ReportResponse {


	// 신고하기(저장)
	public static class SaveDTO{
		private Long reportId;
		private Long postId;
		private Long userId;
		private Timestamp createdAt;

		@Builder
		public SaveDTO(Report report) {
			this.reportId = report.getReportId();
			this.postId = report.getUserCommunity().getPostId();
			this.userId = report.getUser().getUserId();
			this.createdAt = report.getCreatedAt();
		}
	}

	@Data
	public static class FindAllDTO {
		private Long reportId;
		private String reporterNickname;
		private Long reportedPostId;
		private String reportedPostTitle;
		private Timestamp createdAt;

		@Builder
		public FindAllDTO(Report report) {
			this.reportId = report.getReportId();
			this.reporterNickname = report.getUser().getUserNickname();
			this.reportedPostId = report.getUserCommunity().getPostId();
			this.reportedPostTitle = report.getUserCommunity().getTitle();
			this.createdAt = report.getCreatedAt();
		}
	}
}
