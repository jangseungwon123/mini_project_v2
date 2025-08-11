package com.tenco.jobpotal.user.applInfo;

import com.tenco.jobpotal._core.common.ApiUtil;
import com.tenco.jobpotal._core.utils.Define;
import com.tenco.jobpotal.user.LoginUser;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ApplInfoRestController {

    private final ApplInfoService appiInfoService;

    @Operation(summary = "지원하기", description = "유저가 공고를 보고 지원")
    @PostMapping("/application/{id}/save")
    public ResponseEntity<?> save(@Valid @RequestBody ApplInfoRequest.SaveDTO saveDTO, Errors errors,
                                  LoginUser loginUser){
        ApplInfoResponse.SaveDTO saveApplInfo = appiInfoService.save(saveDTO,loginUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiUtil<>("채용공고에 지원하셨습니다."));
    }

    @Operation(summary = "합격", description = "회사가 입사한 지원자에게 합격/불합격 통보")
    @PutMapping("/application/pass")
    public ResponseEntity<?> pass(@Valid @RequestBody ApplInfoRequest.UpdateStatusDTO updateStatusDTO, Errors errors,
                                  @RequestAttribute(Define.LOGIN_USER) LoginUser loginUser){
        ApplInfoResponse.UpdateStatusDTO pass = appiInfoService.updateStatus(updateStatusDTO,"합격", loginUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiUtil<>("합격처리하셨습니다."));
    }

    @Operation(summary = "불합격", description = "회사가 입사한 지원자에게 합격/불합격 통보")
    @PutMapping("/application/fail")
    public ResponseEntity<?> fail(@Valid @RequestBody ApplInfoRequest.UpdateStatusDTO updateStatusDTO, Errors errors,
                                  @RequestAttribute(Define.LOGIN_USER) LoginUser loginUser){
        ApplInfoResponse.UpdateStatusDTO pass = appiInfoService.updateStatus(updateStatusDTO,"불합격", loginUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiUtil<>("불합격처리하셨습니다."));
    }

    @Operation(summary = "[사용자]나의 지원 목록" ,description = "[사용자]가 자신의 지원 목록을 조회")
    @GetMapping("/application/userapply/{userId}/list")
    public ResponseEntity<?> userApplyList(@PathVariable(name = "userId") Long userId,
                                           @RequestAttribute(Define.LOGIN_USER) LoginUser loginUser) {
        return ResponseEntity.ok(appiInfoService.userApplInfoListDTO(loginUser, userId));
    }

    @Operation(summary = "[기업]지원자 목록" ,description = "[기업] 공고에 지원한 지원자 목록을 조회")
    @GetMapping("/application/compapply/{compId}/list")
    public ResponseEntity<?> compApplyList(@PathVariable(name = "compId") Long compId,
                                           @RequestAttribute(Define.LOGIN_USER) LoginUser loginUser) {
        return ResponseEntity.ok(appiInfoService.compApplInfoListDTOList(loginUser, compId));
    }

    @Operation(summary = "[공고]에서 삭제" ,description = "[채용 공고] 안에 삭제하는 기능")
    @DeleteMapping("/application/userapply/{applyId}/delete")
    public ResponseEntity<?> postApplyDelete(@PathVariable(name = "applyId") Long applyId,
                                             @RequestAttribute(Define.LOGIN_USER) LoginUser loginUser){
        appiInfoService.deleteByapply(loginUser, applyId);
        return ResponseEntity.ok(new ApiUtil<>("삭제 완료"));
    }


//    @Operation(summary = "[지원목록]에서 삭제" ,description = "[지원 목록] 안에 삭제하는 기능")
//    @DeleteMapping("/application/userapplylist/{applyId}/delete")
//    public ResponseEntity<?> postApplyListDelete(@PathVariable(name = "applyId") Long applyId,
//                                                 @RequestAttribute(Define.LOGIN_USER) LoginUser loginUser){
//        appiInfoService.deleteByApplyList(applyId,loginUser);
//       return ResponseEntity.ok(new ApiUtil<>("삭제 완료"));
//    }

}
