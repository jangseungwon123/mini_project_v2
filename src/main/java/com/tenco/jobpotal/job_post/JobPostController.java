package com.tenco.jobpotal.job_post;

import com.tenco.jobpotal._core.utils.Define;
import com.tenco.jobpotal.user.LoginUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/api/jobposts")
public class JobPostController {

    private final JobPostService jobPostService;

    public JobPostController(JobPostService jobPostService) {
        this.jobPostService = jobPostService;
    }

    // 전체 목록 조회
    @GetMapping
    public ResponseEntity<List<JobPostResponseDTO>> getAllJobPosts() {
        List<JobPostResponseDTO> jobPosts = jobPostService.getAllJobPosts();
        return ResponseEntity.ok(jobPosts);
    }

    // 상세 보기 조회
    @GetMapping("/{recruitId}")
    public ResponseEntity<JobPostResponseDTO> getJobPostById(@PathVariable Long recruitId) {
        JobPost jobPost = jobPostService.getJobPostById(recruitId); // 이 메서드는 예외 던지므로 Optional 아님
        return ResponseEntity.ok(new JobPostResponseDTO(jobPost));
    }


    // 게시글 등록
    @PostMapping("/save")
    public ResponseEntity<JobPostResponseDTO> createJobPost(@RequestBody @Valid JobPostRequestDTO requestDTO,
                                                            @RequestAttribute(value = Define.LOGIN_USER, required = false)LoginUser loginUser) {
        JobPostResponseDTO created = jobPostService.createJobPost(requestDTO, loginUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // 게시글 수정
    @PutMapping("/update/{recruitId}")
    public ResponseEntity<JobPostResponseDTO> updateJobPost(
            @PathVariable Long recruitId,
            @RequestBody @Valid JobPostRequestDTO requestDTO) {

        JobPostResponseDTO updated = jobPostService.updateJobPost(recruitId, requestDTO);
        return ResponseEntity.ok(updated);
    }


    // 게시글 삭제
    @DeleteMapping("/delete/{recruitId}")
    public ResponseEntity<Void> deleteJobPost(@PathVariable Long recruitId) {
        jobPostService.deleteJobPost(recruitId);
        return ResponseEntity.noContent().build();
    }


    // 페이징 처리
    @GetMapping("/paged")
    public ResponseEntity<Page<JobPostResponseDTO>> getPagedJobPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "postedAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {

        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<JobPostResponseDTO> pagedResult = jobPostService.getJobPostsPaged(pageable);
        return ResponseEntity.ok(pagedResult);
    }

    // 검색 기능
    @GetMapping("/search")
    public ResponseEntity<List<JobPostResponseDTO>> searchJobPosts(@RequestParam String keyword) {
        List<JobPostResponseDTO> result = jobPostService.searchJobPosts(keyword);
        return ResponseEntity.ok(result);
    }
}
