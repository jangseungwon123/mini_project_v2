package com.tenco.jobpotal.community.community1;

import com.tenco.jobpotal._core.common.ApiUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CommunityRestController {

    private static final Logger log = LoggerFactory.getLogger(CommunityRestController.class);
    private final CommunityService communityService;

    // 전체 게시글 조회 (페이징)
    @GetMapping("/community/list")
    public ResponseEntity<?> communityList(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size) {
        Page<Community> communityPage = communityService.findAllPosts(PageRequest.of(page, size));
        return ResponseEntity.ok(new ApiUtil<>(communityPage));
    }

    // 단일 게시글 조회
    @GetMapping("/community/{id}")
    public ResponseEntity<?> getCommunity(@PathVariable(name = "id") Long id) {

        log.info(">> 게시글 조회 시작 << id: {}", id);
        // 게시글 조회 서비스 호출
        // 게시글 없으면 예외처리

        Community post = communityService.findById(id);
        return ResponseEntity.ok(new ApiUtil<>(post));
    }

    // 게시글 생성
    @PostMapping("/community")
    public ResponseEntity<?> createCommunity(@RequestBody CommunityRequest.SaveDTO dto) {
        Community saved = communityService.savePost(dto);
        return ResponseEntity.ok(new ApiUtil<>(saved));
    }

    // 게시글 수정
    @PutMapping("/community/{id}")
    public ResponseEntity<?> updateCommunity(@PathVariable Long id,
                                             @RequestBody CommunityRequest.UpdateDTO dto) {
        communityService.updatePost(id, dto);
        return ResponseEntity.ok(new ApiUtil<>("수정 완료"));
    }

    // 게시글 삭제
    @DeleteMapping("/community/{id}")
    public ResponseEntity<?> deleteCommunity(@PathVariable Long id) {
        communityService.deletePost(id);
        return ResponseEntity.ok(new ApiUtil<>("삭제 완료"));
    }
}

