package com.tenco.jobpotal.scrap;

import com.tenco.jobpotal._core.common.ApiUtil;
import com.tenco.jobpotal._core.utils.Define;
import com.tenco.jobpotal.user.LoginUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CompScrapRestController {

    private final CompScrapService compScrapService;


    @PostMapping("/user_sub")
    public ResponseEntity<?> save(@Valid @RequestBody CompScrapRequest.SaveDTO saveDTO, Errors errors,
                                  @RequestAttribute(Define.LOGIN_USER) LoginUser loginUser) {
        CompScrapResponse.SaveDTO responseDTO = compScrapService.save(saveDTO, loginUser);
        return ResponseEntity.ok(new ApiUtil<>(responseDTO));
    }

    @GetMapping("/user_sub/list")
    public ResponseEntity<?> list(@RequestAttribute(Define.LOGIN_USER) LoginUser loginUser) {
      List<CompScrapResponse.SubListDTO> userSubList = compScrapService.findAllByUserAndCompanyId(loginUser.getId());
      return ResponseEntity.ok(new ApiUtil<>(userSubList));
    }


    @PostMapping("/user_sub/{id}/delete")
    public ResponseEntity<ApiUtil<String>> delete(@PathVariable(name = "id") Long id,
                                                  @RequestAttribute(Define.LOGIN_USER) LoginUser loginUser) {
        compScrapService.deleteById(id, loginUser);
        return ResponseEntity.ok(new ApiUtil<>("구독 삭제 완료"));
    }

}
