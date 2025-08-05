package com.tenco.jobpotal.scrap;

import com.tenco.jobpotal._core.errors.exception.Exception403;
import com.tenco.jobpotal._core.errors.exception.Exception404;
import com.tenco.jobpotal.company.CompInfo;
import com.tenco.jobpotal.company.CompInfoJpaRepository;
import com.tenco.jobpotal.resume.Resume;
import com.tenco.jobpotal.resume.ResumeJpaRepository;
import com.tenco.jobpotal.user.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CompScrapService {
    private final CompScrapJpaRepository compScrapJpaRepository;
    private final ResumeJpaRepository resumeJpaRepository;
    private final CompInfoJpaRepository compInfoJpaRepository;

    // 이력서 스크랩
    @Transactional
    public CompScrapResponse.SaveDTO save(CompScrapRequest.SaveDTO saveDTO, LoginUser loginUser) {

        CompInfo compInfo = compInfoJpaRepository.findByCompInfo(loginUser.getId()).orElseThrow(() ->
                new Exception404("존재하지 않는 기업입니다."));
        if (compScrapJpaRepository.existsByResumeIdAndCompId(saveDTO.getResumeId(), compInfo.getCompId())) {
            throw new Exception403("이미 저장한 이력서 입니다.");
        }
        Resume resume = resumeJpaRepository.findById(saveDTO.getResumeId()).orElseThrow(() ->
                new Exception404("존재하지 않는 이력서입니다."));

        CompScrap compScrap = CompScrap.builder()
                .compInfo(compInfo)
                .resume(resume)
                .build();

        CompScrap savedCompScrap = compScrapJpaRepository.save(compScrap);
        return new CompScrapResponse.SaveDTO(savedCompScrap);
    }

    // 이력서 스크랩 목록 조회 서비스
    public List<CompScrapResponse.ScrapListDTO> findAllByCompUserId(Long compUserId) {
        List<CompScrap> compScrapList = compScrapJpaRepository.findAllByCompUserId(compUserId);
        return compScrapList.stream()
                .map(CompScrapResponse.ScrapListDTO::new)
                .collect(Collectors.toList());
    }

//    public List<CompScrapResponse.ScrapListDTO> findAllByUserAndCompanyId(Long companyId) {
//        List<CompScrapResponse.ScrapListDTO> compScrapList = compScrapJpaRepository.findAllByResumeAndCompId(companyId);
//        return compScrapList;
//    }

    // 이력서 삭제
    @Transactional
    public void deleteById(Long compScrapId, LoginUser loginUser) {
        CompScrap compScrap = compScrapJpaRepository.findById(compScrapId).orElseThrow(() ->
                new Exception404("삭제하려는 이력서가 없습니다"));
        if (!compScrap.isOwner(loginUser.getId())) {
            throw new Exception403("본인이 저장한 이력서만 삭제할 수 있습니다");
        }
        compScrapJpaRepository.deleteById(compScrapId);
    }
}
