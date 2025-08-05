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
public class UserScrapRestController {

    private final UserScrapService userScrapService;


    @PostMapping("/user_scrap")
    public ResponseEntity<?> save(@Valid @RequestBody UserScrapRequest.SaveDTO saveDTO, Errors errors,
                                  @RequestAttribute(Define.LOGIN_USER) LoginUser loginUser) {
        UserScrapResponse.SaveDTO responseDTO = userScrapService.save(saveDTO, loginUser);
        return ResponseEntity.ok(new ApiUtil<>(responseDTO));
    }

    @GetMapping("/user_scrap/list")
    public ResponseEntity<?> list(@RequestAttribute(Define.LOGIN_USER) LoginUser loginUser) {
      List<UserScrapResponse.ScrapListDTO> userScrapList = userScrapService.findAllByUserAndJobPostId(loginUser.getId());
      return ResponseEntity.ok(new ApiUtil<>(userScrapList));
    }


    @PostMapping("/user_scrap/{id}/delete")
    public ResponseEntity<ApiUtil<String>> delete(@PathVariable(name = "id") Long id,
                                                  @RequestAttribute(Define.LOGIN_USER) LoginUser loginUser) {
        userScrapService.deleteById(id, loginUser);
        return ResponseEntity.ok(new ApiUtil<>("채용공고 삭제 완료"));
    }

}
