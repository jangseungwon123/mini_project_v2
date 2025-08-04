package com.tenco.jobpotal.resume;

import com.tenco.jobpotal._core.common.ApiUtil;
import com.tenco.jobpotal._core.utils.Define;
import com.tenco.jobpotal.user.LoginUser;
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

    @PostMapping("/api/resumes")
    public ResponseEntity<?> save(@Valid @RequestBody ResumeRequest.SaveDTO saveDTO,
                                  Errors errors,
                                  @RequestAttribute(Define.LOGIN_USER)LoginUser loginUser) {

        ResumeResponse.SaveDTO saveResume = resumeService.save(saveDTO,loginUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiUtil<>(saveResume));
    }

    @PostMapping("/api/resumes/{id}")
    public ResponseEntity<?> update(@Valid @PathVariable(name = "id")Long id,
                                    @RequestBody ResumeRequest.UpdateDTO updateDTO,
                                    Errors errors,
                                    @RequestAttribute(Define.LOGIN_USER)LoginUser loginUser) {

        ResumeResponse.UpdateDTO updateResume = resumeService.update(id, updateDTO, loginUser);
        return ResponseEntity.ok(new ApiUtil<>(updateResume));
    }

    @DeleteMapping("/api/resumes/{id}")
    public ResponseEntity<ApiUtil<String>> delete(
            @PathVariable(name = "id") Long id,
            @RequestAttribute(Define.LOGIN_USER) LoginUser loginUser) {
        resumeService.deleteById(id, loginUser);
        return ResponseEntity.ok(new ApiUtil<>("이력서 삭제 성공"));
    }

}
