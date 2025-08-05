package com.tenco.jobpotal.job_post;

import com.tenco.jobpotal._core.errors.exception.Exception404;
import com.tenco.jobpotal.company.CompInfo;
import com.tenco.jobpotal.company.CompInfoJpaRepository;
import com.tenco.jobpotal.user.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class JobPostService {
    private final JobPostRepository jobPostRepository;
    private final CompInfoJpaRepository compInfoJpaRepository;


    // 전체 목록 조회
    public List<JobPostResponseDTO> getAllJobPosts() {
        List<JobPost> jobPosts = jobPostRepository.findAll();
        return jobPosts.stream()
                .map(JobPostResponseDTO::new)
                .collect(Collectors.toList());
    }

    // 상세 조회 (예외 처리 포함)
    public JobPost getJobPostById(Long recruitId) {
        return jobPostRepository.findById(recruitId)
                .orElseThrow(() -> new Exception404("해당 채용공고가 존재하지 않습니다."));
    }

    // 게시글 등록
    public JobPostResponseDTO createJobPost(JobPostRequestDTO requestDTO, LoginUser loginUser) {
        CompInfo companyInfo = compInfoJpaRepository.findById(requestDTO.getCompId()).orElseThrow(() ->
                new Exception404("해당 게시물이 존재하지 않습니다.")
        );
        JobPost savedJobPost = jobPostRepository.save(requestDTO.toEntity(companyInfo, loginUser));
        return new JobPostResponseDTO(savedJobPost);
    }

    // 게시글 수정 (예외 처리 포함)
    public JobPostResponseDTO updateJobPost(Long recruitId, JobPostRequestDTO requestDTO) {
        JobPost jobPost = jobPostRepository.findById(recruitId)
                .orElseThrow(() -> new Exception404("존재하지 않는 게시글입니다."));

        jobPost.setTitle(requestDTO.getTitle());
        jobPost.setContent(requestDTO.getContent());
        jobPost.setRequireCareerYears(requestDTO.getRequireCareerYears());
        jobPost.setEmploymentType(requestDTO.getEmploymentType());
        jobPost.setPostedAt(requestDTO.getPostedAt());
        jobPost.setDeadline(requestDTO.getDeadline());

        JobPost updated = jobPostRepository.save(jobPost);
        return new JobPostResponseDTO(updated);
    }

    // 게시글 삭제 (예외 처리 포함)
    public void deleteJobPost(Long recruitId) {
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
