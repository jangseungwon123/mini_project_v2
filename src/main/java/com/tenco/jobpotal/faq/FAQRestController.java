package com.tenco.jobpotal.faq;

import com.tenco.jobpotal._core.errors.exception.Exception403;
import com.tenco.jobpotal._core.utils.Define;
import com.tenco.jobpotal.user.LoginUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/faq")
public class FAQRestController {
    private final FAQService faqService;

    // 등록
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody FAQRequestDTO dto, @RequestAttribute(Define.LOGIN_USER) LoginUser loginUser){
        // [보안] 관리자 권한 확인
        if (!loginUser.isAdmin()) {
            throw new Exception403("FAQ를 등록할 권한이 없습니다.");
        }
        // [개선] var 대신 명시적인 타입 사용으로 가독성 향상
        FAQResponseDTO createdFaq = faqService.create(dto, loginUser);
        // [개선] 생성 성공 시 201 Created 상태 코드 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFaq);
    }

    // 전체 조회
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(faqService.getAll());
    }

    // 상세 조회
    @GetMapping("/{faqId}")
    public ResponseEntity<?> getById(@PathVariable Long faqId) {
        return ResponseEntity.ok(faqService.getById(faqId));
    }

    // 수정
    @PutMapping("/update/{faqId}")
    public ResponseEntity<?> update(@PathVariable Long faqId, @Valid @RequestBody FAQRequestDTO dto, @RequestAttribute(Define.LOGIN_USER) LoginUser loginUser){
        // [보안] 관리자 권한 확인
        if (!loginUser.isAdmin()) {
            throw new Exception403("FAQ를 수정할 권한이 없습니다.");
        }
        return ResponseEntity.ok(faqService.update(faqId,dto));
    }

    // 삭제
    @DeleteMapping("/delete/{faqId}")
    public ResponseEntity<?> delete(@PathVariable Long faqId, @RequestAttribute(Define.LOGIN_USER) LoginUser loginUser) {
        // [보안] 관리자 권한 확인
        if (!loginUser.isAdmin()) {
            throw new Exception403("FAQ를 삭제할 권한이 없습니다.");
        }
        faqService.delete(faqId);
        return ResponseEntity.noContent().build();
    }

}
