package com.tenco.jobpotal.job_post;

import com.tenco.jobpotal._core.errors.exception.Exception404;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobPostService {
    private final JobPostRepository jobPostRepository;

    public JobPostService(JobPostRepository jobPostRepository) {
        this.jobPostRepository = jobPostRepository;
    }

    // 전체 목록 조회
    public List<JobPostResponseDTO> getAllJobPosts() {
        List<JobPost> jobPosts = jobPostRepository.findAll();
        return jobPosts.stream()
                .map(JobPostResponseDTO::new)
                .collect(Collectors.toList());
    }

    // 상세 조회 (예외 처리 포함)
    public JobPost getJobPostById(Integer recruitId) {
        return jobPostRepository.findById(recruitId)
                .orElseThrow(() -> new Exception404("해당 채용공고가 존재하지 않습니다."));
    }

    // 게시글 등록
    public JobPostResponseDTO createJobPost(JobPostRequestDTO requestDTO) {
        JobPost jobPost = new JobPost();
        jobPost.setCompId(requestDTO.getCompId());
        jobPost.setTitle(requestDTO.getTitle());
        jobPost.setContent(requestDTO.getContent());
        jobPost.setRequireCareerYears(requestDTO.getRequireCareerYears());
        jobPost.setEmploymentType(requestDTO.getEmploymentType());
        jobPost.setInstId(requestDTO.getInstId());
        jobPost.setPostedAt(requestDTO.getPostedAt());
        jobPost.setDeadline(requestDTO.getDeadline());

        JobPost savedJobPost = jobPostRepository.save(jobPost);
        return new JobPostResponseDTO(savedJobPost);
    }

    // 게시글 수정 (예외 처리 포함)
    public JobPostResponseDTO updateJobPost(Integer recruitId, JobPostRequestDTO requestDTO) {
        JobPost jobPost = jobPostRepository.findById(recruitId)
                .orElseThrow(() -> new Exception404("존재하지 않는 게시글입니다."));

        jobPost.setTitle(requestDTO.getTitle());
        jobPost.setContent(requestDTO.getContent());
        jobPost.setRequireCareerYears(requestDTO.getRequireCareerYears());
        jobPost.setEmploymentType(requestDTO.getEmploymentType());
        jobPost.setPostedAt(requestDTO.getPostedAt());
        jobPost.setDeadline(requestDTO.getDeadline());
        jobPost.setInstId(requestDTO.getInstId());

        JobPost updated = jobPostRepository.save(jobPost);
        return new JobPostResponseDTO(updated);
    }

    // 게시글 삭제 (예외 처리 포함)
    public void deleteJobPost(Integer recruitId) {
        if (!jobPostRepository.existsById(recruitId)) {
            throw new Exception404("해당 게시글이 존재하지 않습니다.");
        }
        jobPostRepository.deleteById(recruitId);
    }

    // 페이징 처리
    public Page<JobPostResponseDTO> getJobPostsPaged(Pageable pageable) {
        Page<JobPost> jobPostsPage = jobPostRepository.findAll(pageable);
        return jobPostsPage.map(JobPostResponseDTO::new);
    }

    // 검색 기능
    public List<JobPostResponseDTO> searchJobPosts(String keyword) {
        List<JobPost> jobPosts = jobPostRepository.searchByKeyword(keyword);
        return jobPosts.stream()
                .map(JobPostResponseDTO::new)
                .toList();
    }
}
