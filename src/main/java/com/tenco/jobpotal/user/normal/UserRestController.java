package com.tenco.jobpotal.user.normal;

import com.tenco.jobpotal._core.common.ApiUtil;
import com.tenco.jobpotal._core.errors.exception.Exception401;
import com.tenco.jobpotal._core.errors.exception.Exception403;
import com.tenco.jobpotal._core.utils.Define;
import com.tenco.jobpotal.user.LoginUser;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserRestController {

    private final UserService userService;

    @Operation(summary = "회원가입")
    @PostMapping("/join")
    public ResponseEntity<?> userJoin(@Valid @RequestBody UserRequest.JoinDTO joinDTO, Errors errors) {
        UserResponse.JoinDTO joinUser = userService.join(joinDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiUtil<>("회원가입 완료 되었습니다."));
    }

    @Operation(summary = "로그인")
    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@Valid @RequestBody UserRequest.LoginDTO loginDTO, Errors errors) {
        String jwtToken = userService.login(loginDTO);
        return ResponseEntity.ok()
                .header("Authorization", "Bearer " +jwtToken)
                .body(new ApiUtil<>(null));
    }

   @Operation(summary = "회원정보 조회")
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserInfo(@PathVariable(name = "id") Long id,
                                         @RequestAttribute(Define.LOGIN_USER) LoginUser loginUser) {
        if (loginUser == null) {
            throw new Exception401("인증 정보가 없습니다");
        }
       if (!id.equals(loginUser.getId())) {
           throw new Exception403("본인 정보만 조회 가능합니다");
       }
        UserResponse.DetailDTO userDetail = userService.findById(loginUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(userDetail));
    }

    @Operation(summary = "회원이 구독한 기업의 채용공고 스킬 매칭리스트 조회")
    @GetMapping("/users/matchJobPost")
    public ResponseEntity<?> getUserMatchingJobSkillFromComp(@RequestAttribute(Define.LOGIN_USER) LoginUser loginUser) {
        if (loginUser == null) {
            throw new Exception401("인증 정보가 없습니다");
        }

        List<UserResponse.JobPostMatchListDTO> userJobMatchListInfo = userService.jobMatchList(loginUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(userJobMatchListInfo));
    }


    @Operation(summary = "회원정보 수정")
    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable(name = "id") Long id,
                                        @RequestAttribute(Define.LOGIN_USER) LoginUser loginUser,
                                        @Valid @RequestBody UserRequest.UpdateProfileRequestDTO updateProfileRequestDTO, Errors errors) {
        if (loginUser == null) {
            throw new Exception401("인증 정보가 없습니다");
        }
        UserResponse.UpdateDTO updateUser = userService.updateById(id, loginUser.getId(), updateProfileRequestDTO);
        return ResponseEntity.ok().body(new ApiUtil<>(updateUser));
    }

    @Operation(summary = "로그아웃")
    @PostMapping("/logout")
    public ResponseEntity<?> userLogout() {
        return ResponseEntity.ok(new ApiUtil<>("로그아웃 성공"));

    }

}

