package com.tenco.jobpotal.reply.job_reply;

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
@RequestMapping("/api")
public class JobCommCmtRestController {

    private final JobCommCmtService jobCommCmtService;

    @Operation(summary = "댓글 저장 기능")
    @PostMapping("/jobcommcmt")
    public ResponseEntity<?> save(@Valid @RequestBody JobCommCmtRequest.SaveDTO saveDTO, Errors errors,
                                  @RequestAttribute(Define.LOGIN_USER)LoginUser loginUser) {

        JobCommCmtResponse.SaveDTO saveJobCommCmt = jobCommCmtService.save(saveDTO,loginUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiUtil<>(saveJobCommCmt));
    }

    @Operation(summary = "댓글 삭제 기능")
    @PostMapping("/jobcommcmt/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long jobCommCmtId,
                                    @RequestParam(name = "jobCommCmtId") Long communityId,
                                    @RequestAttribute(Define.LOGIN_USER)LoginUser loginUser) {

        jobCommCmtService.deleteById(jobCommCmtId,loginUser);

        return ResponseEntity.ok(new ApiUtil<>("댓글 삭제 성공"));
    }

}
