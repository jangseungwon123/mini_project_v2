package com.tenco.jobpotal.user;

import com.tenco.jobpotal._core.common.ApiUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserRestController {

    private final UserService userService;

    @Operation(summary = "회원가입")
    @PostMapping("/join")
    public ResponseEntity<?> join(@Valid @RequestBody UserRequest.JoinDTO joinDTO, Errors errors) {
        UserResponse.JoinDTO joinUser = userService.join(joinDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiUtil<>(joinUser));
    }

    @Operation(summary = "로그인")
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserRequest.LoginDTO loginDTO, Errors errors) {
        String jwtToken = userService.login(loginDTO);
        return ResponseEntity.ok()
                .header("Authorization", "Bearer " +jwtToken)
                .body(new ApiUtil<>(null));
    }


//   @Operation(summary = "회원정보 조회")
//    @GetMapping("/api/users/{id}")
//    public ResponseEntity<?> getUserInfo(@PathVariable(name = "id") Long id,
//                                         @RequestAttribute(Define.SESSION_USER) LoginUser loginUser) {
//        // 인증 체크
//        if (loginUser == null) {
//            throw new Exception401("인증 정보가 없습니다");
//        }
//        UserResponse.DetailDTO userDetail = userService.findById(id, loginUser.getId());
//        return ResponseEntity.ok(new ApiUtil<>(userDetail));
//    }
//
//    @Operation(summary = "회원정보 수정")
//    @PutMapping("/api/users/{id}")
//    public ResponseEntity<?> updateUser(@PathVariable(name = "id") Long id,
//                                        @RequestAttribute(Define.SESSION_USER) LoginUser loginUser,
//                                        @Valid @RequestBody UserRequest.UpdateDTO updateDTO, Errors errors) {
//        // 인증 체크
//        if (loginUser == null) {
//            throw new Exception401("인증 정보가 없습니다");
//        }
//        UserResponse.UpdateDTO updateUser = userService.updateById(id, loginUser.getId(), updateDTO);
//        return ResponseEntity.ok().body(new ApiUtil<>(updateUser));
//    }


    @Operation(summary = "로그아웃")
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok(new ApiUtil<>("로그아웃 성공"));

    }

}

