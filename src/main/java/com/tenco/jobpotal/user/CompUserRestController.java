package com.tenco.jobpotal.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "CompUser", description = "기업회원 관리 API")
public class CompUserRestController {
	private final CompUserService compUserService;

	// 회원가입
	// 로그인
	// 회원정보조회
	// 회원정보수정
	// 로그아웃
//	@Operation(summary = "로그아웃", description = "로그아웃")
//	@PostMapping("/logout")
//	public ResponseEntity<?> logout() {
//		return ResponseEntity.ok(new ApiUtil<>("로그아웃 성공"));
//	}

}
