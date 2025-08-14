package com.tenco.jobpotal.user.applInfo;

import com.tenco.jobpotal._core.common.ApiUtil;
import com.tenco.jobpotal._core.utils.Define;
import com.tenco.jobpotal.user.LoginUser;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ApplInfoRestController {

    private final ApplInfoService applInfoService;

    @Operation(summary = "지원하기", description = "유저가 공고를 보고 지원")
    @PostMapping("/application/save")
    public ResponseEntity<?> save(@Valid @RequestBody ApplInfoRequest.SaveDTO saveDTO, Errors errors,
                                  @RequestAttribute(Define.LOGIN_USER) LoginUser loginUser) {
        ApplInfoResponse.SaveDTO saveApplInfo = applInfoService.save(saveDTO, loginUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiUtil<>("채용공고에 지원하셨습니다."));
    }

    @Operation(summary = "합격", description = "회사가 입사한 지원자에게 합격/불합격 통보")
    @PutMapping("/application/{applyId}/pass")
    public ResponseEntity<?> pass(@PathVariable(name = "applyId") Long applyId,
                                  @RequestAttribute(Define.LOGIN_USER) LoginUser loginUser) {
        applInfoService.updateStatus(applyId, "합격", loginUser);
        return ResponseEntity.ok(new ApiUtil<>("합격처리하셨습니다."));
    }

    @Operation(summary = "불합격", description = "회사가 입사한 지원자에게 합격/불합격 통보")
    @PutMapping("/application/{applyId}/fail")
    public ResponseEntity<?> fail(@PathVariable(name = "applyId") Long applyId,
                                  @RequestAttribute(Define.LOGIN_USER) LoginUser loginUser) {
        applInfoService.updateStatus(applyId, "불합격", loginUser);
        return ResponseEntity.ok(new ApiUtil<>("불합격처리하셨습니다."));
    }

    @Operation(summary = "[사용자]나의 지원 목록", description = "[사용자]가 자신의 지원 목록을 조회")
    @GetMapping("/application/userapply/{userId}/list")
    public ResponseEntity<?> userApplyList(@PathVariable(name = "userId") Long userId,
                                           @RequestAttribute(Define.LOGIN_USER) LoginUser loginUser) {
        return ResponseEntity.ok(applInfoService.userApplInfoListDTO(loginUser, userId));
    }

    @Operation(summary = "[기업]지원자 목록", description = "[기업] 공고에 지원한 지원자 목록을 조회")
    @GetMapping("/application/compapply/{compId}/list")
    public ResponseEntity<?> compApplyList(@PathVariable(name = "compId") Long compId,
                                           @RequestAttribute(Define.LOGIN_USER) LoginUser loginUser) {
        return ResponseEntity.ok(applInfoService.compApplInfoListDTOList(loginUser, compId));
    }

    @Operation(summary = "[공고]에서 삭제", description = "[채용 공고] 안에 삭제하는 기능")
    @DeleteMapping("/application/userapply/{applyId}/delete")
    public ResponseEntity<?> postApplyDelete(@PathVariable(name = "applyId") Long applyId,
                                             @RequestAttribute(Define.LOGIN_USER) LoginUser loginUser) {
        applInfoService.deleteByapply(loginUser, applyId);
        return ResponseEntity.ok(new ApiUtil<>("삭제 완료"));
    }
}
