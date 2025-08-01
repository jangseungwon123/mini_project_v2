package com.tenco.jobpotal.user.comp_user;

import jakarta.validation.constraints.Size;
import lombok.Data;

public class CompUserRequest {

	// 회원정보수정 DTO
	@Data
	public static class UpdateDTO {

		@Size(min = 8, max = 20)
		private String compUserPassword; // 로그인 비밀번호
		private String compUserName;
		private String compUserPhone;
		private String compUserEmail;
		private String compUserNickname;
		private String compRegNumber; // 사업자등록번호

	}
}
