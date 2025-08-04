package com.tenco.jobpotal.subscribe;

import com.tenco.jobpotal._core.common.ApiUtil;
import com.tenco.jobpotal._core.utils.Define;
import com.tenco.jobpotal.user.LoginUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "CompSub", description = "기업입장의 구독 관리 API")
public class CompSubRestController {

    private final CompSubService compSubService;

    @Operation(summary = "구독저장", operationId = "CompSubSave")
    @PostMapping("/api/comp_sub")
    public ResponseEntity<?> save(@Valid @RequestBody CompSubRequest.SaveDTO saveDTO, Errors error,
                                  @RequestAttribute(Define.LOGIN_COMP_USER)LoginUser loginUser) {
        CompSubResponse.SaveDTO responseDTO = compSubService.save(saveDTO, loginUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiUtil<>(responseDTO));
    }

    @GetMapping("/api/comp_sub/list")
    public ResponseEntity<?> list(@RequestAttribute(Define.LOGIN_COMP_USER) LoginUser loginUser) {
        List<CompSubResponse.SubListDTO> userSubList = compSubService.findAllByUserAndCompanyId(loginUser.getId());
        return ResponseEntity.ok(new ApiUtil<>(userSubList));
    }


    @PostMapping("/api/comp_sub/{id}/delete")
    public ResponseEntity<ApiUtil<String>> delete(@PathVariable(name = "id") Long id,
                                                  @RequestAttribute(Define.LOGIN_USER) LoginUser loginUser) {
        compSubService.deleteById(id, loginUser);
        return ResponseEntity.ok(new ApiUtil<>("구독 삭제 완료"));
    }

}
