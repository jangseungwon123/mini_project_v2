package com.tenco.jobpotal.user;

import com.tenco.jobpotal._core.common.ApiUtil;
import com.tenco.jobpotal._core.utils.Define;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "CompUser", description = "기업회원 관리 API")
public class CompUserRestController {
	private final CompUserService compUserService;

	@Operation(summary = "회원가입")
	@PostMapping("/join")
	public ResponseEntity<?> join(@Valid @RequestBody CompUserRequest.JoinDTO joinDTO, Errors errors) {
		CompUserResponse.JoinDTO joinedCompUser = compUserService.join(joinDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiUtil<>(joinedCompUser));
	}

//	@Operation(summary = "로그인")
//	@PostMapping("/login")
//	public ResponseEntity<?> login(@Valid @RequestBody CompUserRequest.LoginDTO loginDTO, Errors errors) {
//		String jwtToken = compUserService.login(loginDTO);
//		return ResponseEntity.ok()
//				.header(Define.AUTH, Define.BEARER + jwtToken)
//				.body(new ApiUtil<>(jwtToken));
//	}

	@Operation(summary = "회원정보조회")
	@GetMapping("/api/compUsers/{compUserId")
	public ResponseEntity<?> getCompUserInfo(
			@PathVariable(name = "compUserId") Long compUserId,
			@RequestAttribute(Define.LOGIN_COMP_USER) LoginUser sessionUser) {
//		if (sessionUser == null) {
//			throw new Exception401("로그인 필요");
//		}
		CompUserResponse.DetailDTO compUserDetail =
				compUserService.findCompUserByCompUserId(sessionUser.getId());
		return ResponseEntity.ok(new ApiUtil<>(compUserDetail));
	}

	@Operation(summary = "회원정보수정")
	@PutMapping("/api/compUsers/{compUserId}")
	public ResponseEntity<?> updateCompUser(
			@PathVariable(name = "compUserId") Long compUserId,
			@RequestAttribute(Define.LOGIN_COMP_USER) LoginUser sessionUser,
			@Valid @RequestBody CompUserRequest.UpdateDTO updateDTO, Errors errors) {
//		if (sessionUser == null) {
//			throw new Exception401("로그인 필요");
//		}
		CompUserResponse.UpdateDTO updatedCompUser =
				compUserService.updateById(compUserId, sessionUser.getId(), updateDTO);
		return ResponseEntity.ok().body(new ApiUtil<>(updatedCompUser));
	}

	@Operation(summary = "로그아웃", description = "로그아웃")
	@PostMapping("/logout")
	public ResponseEntity<?> logout() {
		return ResponseEntity.ok(new ApiUtil<>("로그아웃 성공"));
	}

}
