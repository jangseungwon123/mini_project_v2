package com.tenco.jobpotal.faq;

import com.tenco.jobpotal._core.utils.Define;
import com.tenco.jobpotal.faq.FAQRequestDTO;
import com.tenco.jobpotal.faq.FAQResponseDTO;
import com.tenco.jobpotal.user.LoginUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faq")
@RequiredArgsConstructor
public class FAQRestController {

    private final FAQService faqService;
    private final HttpSession session;

    // 전체 목록
    @GetMapping
    public ResponseEntity<List<FAQResponseDTO>> getAll() {
        return ResponseEntity.ok(faqService.getAllFAQs());
    }

    // 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<FAQResponseDTO> getDetail(@PathVariable Long id) {
        return ResponseEntity.ok(faqService.getFAQ(id));
    }

    // 등록 (관리자만)
    @PostMapping("/save")
    public ResponseEntity<?> create(@RequestBody FAQRequestDTO dto,@RequestAttribute(Define.LOGIN_USER)LoginUser loginUser) {
        if (!isAdmin(loginUser)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("관리자만 등록 가능합니다.");
        }
        System.out.println("로그인 유저의 아이디 : " + loginUser.getLoginId());
        return ResponseEntity.ok(faqService.createFAQ(dto));
    }

    // 수정 (관리자만)
    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody FAQRequestDTO dto,@RequestAttribute(Define.LOGIN_USER)LoginUser loginUser) {
        if (!isAdmin(loginUser)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("관리자만 수정 가능합니다.");
        }
        return ResponseEntity.ok(faqService.updateFAQ(id, dto));
    }

    // 삭제 (관리자만)
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id,@RequestAttribute(Define.LOGIN_USER)LoginUser loginUser) {
        if (!isAdmin(loginUser)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("관리자만 삭제 가능합니다.");
        }
        faqService.deleteFAQ(id);
        return ResponseEntity.ok("삭제 완료");
    }

    // 관리자 여부 체크
    private boolean isAdmin(@RequestAttribute(Define.LOGIN_USER)LoginUser loginUser) {
        return loginUser != null && loginUser.isAdmin();
    }
}
