package com.tenco.jobpotal.resume;

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
public class ResumeRestController {

    private final ResumeService resumeService;

    @Operation(summary = "이력서 작성")
    @PostMapping("/api/resumes")
    public ResponseEntity<?> save(@Valid @RequestBody ResumeRequest.SaveDTO saveDTO,
                                  Errors errors,
                                  @RequestAttribute(Define.LOGIN_USER)LoginUser loginUser) {

        ResumeResponse.SaveDTO saveResume = resumeService.save(saveDTO,loginUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiUtil<>(saveResume));
    }

    @Operation(summary = "이력서 수정")
    @PostMapping("/api/resumes/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable(name = "id")Long id,
                                    @RequestBody ResumeRequest.UpdateDTO updateDTO,
                                    Errors errors,
                                    @RequestAttribute(Define.LOGIN_USER)LoginUser loginUser) {

        ResumeResponse.UpdateDTO updateResume = resumeService.update(id, updateDTO, loginUser);
        return ResponseEntity.ok(new ApiUtil<>(updateResume));
    }

    @Operation(summary = "이력서 삭제")
    @DeleteMapping("/api/resumes/{id}")
    public ResponseEntity<ApiUtil<String>> delete(
            @PathVariable(name = "id") Long id,
            @RequestAttribute(Define.LOGIN_USER) LoginUser loginUser) {
        resumeService.deleteById(id, loginUser);
        return ResponseEntity.ok(new ApiUtil<>("이력서 삭제 성공"));
    }

}
