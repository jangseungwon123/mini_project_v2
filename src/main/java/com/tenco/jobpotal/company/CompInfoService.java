package com.tenco.jobpotal.company;

import com.tenco.jobpotal._core.errors.exception.Exception400;
import com.tenco.jobpotal._core.errors.exception.Exception404;
import com.tenco.jobpotal.user.CompUser;
import com.tenco.jobpotal.user.CompUserResponse;
import com.tenco.jobpotal.user.CompUserService;
import com.tenco.jobpotal.user.LoginUser;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CompInfoService {

    private static final Logger log = LoggerFactory.getLogger(CompInfoService.class);

    private final CompInfoJpaRepository compInfoJpaRepository;

    //기업목록 조회(페이지)
    public List<CompInfoResponse.MainDTO> findAllCompanyInfo(int page, int size, String keyword) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<CompInfo> compInfosPage;
        if (keyword != null && !keyword.trim().isEmpty()){
            compInfosPage = compInfoJpaRepository.findAllCompInfoByKeyword(pageable, keyword);
        } else {
            compInfosPage = compInfoJpaRepository.findAllCompInfo(pageable);
        }

        List<CompInfoResponse.MainDTO> compInfoList = new ArrayList<>();

        for (CompInfo compInfo : compInfosPage.getContent()) {
            CompInfoResponse.MainDTO mainDTO = new CompInfoResponse.MainDTO(compInfo);
            compInfoList.add(mainDTO);
        }
        return compInfoList;
    }

    // 기업정보 상세조회
    public CompInfoResponse.DetailDTO findCompanyInfoById(Long id, LoginUser loginUser) {

        CompInfo companyInfo = compInfoJpaRepository.findById(id).orElseThrow(() ->
                new Exception404("해당 게시물이 존재하지 않습니다.")
        );

        return new CompInfoResponse.DetailDTO(companyInfo, loginUser);
    }

    // 기업정보 insert
    @Transactional
    public CompInfo companyInfoInsert(CompUser compUser, CompInfoRequest.SaveDTO saveDTO) {

        return compInfoJpaRepository.save(saveDTO.toEntity(compUser));
    }


    // 기업정보 업데이트
    @Transactional
    public CompInfo companyInfoUpdate(Long id, CompInfoRequest.UpdateDTO updateDTO, LoginUser loginUser) {

        CompInfo updateCompanyInfo = compInfoJpaRepository.findById(id).orElseThrow(() ->
                new Exception404("해당 게시물이 존재하지 않습니다.")
        );

        log.info("updateCompanyInfo 값 확인 : {}", updateCompanyInfo);

        if (!updateCompanyInfo.getCompUser().getCompUserId().equals(loginUser.getId())) {
            throw new Exception400("해당 기업정보를 등록한 기업회원만 수정 할 수 있습니다.");
        }

        // 더티체킹 사용.
        updateCompanyInfo.update(updateDTO);

        return updateCompanyInfo;
    }

    @Transactional
    public void companyInfoDelete(LoginUser loginUser, Long id) {

        log.info("게시글 삭제 서비스 시작 - ID {}", id);
        CompInfo companyInfo = compInfoJpaRepository.findById(id).orElseThrow(() ->
                new Exception404("삭제하려는 게시글이 없습니다")
        );

        if (!companyInfo.getCompUser().getCompUserId().equals(loginUser.getId())) {
            throw new Exception400("해당 기업정보를 등록한 기업회원만 삭제할 수 있습니다.");
        }

        compInfoJpaRepository.deleteById(id);
    }

    /*
    public Page<CompanyReview> findCompanyReviewByCompanyId(Pageable pageable, Long companyId) {

        log.info("기업 리뷰 조회 서비스 시작");

        return companyReviewJpaRepository.findReviewsJoinCompanyInfo(pageable, companyId);
    }

    public List<CompanyReview> findCompanyReviewByCompanyId(Long companyId) {

        log.info("기업 리뷰 조회 서비스 시작");

        return companyReviewJpaRepository.findReviewsJoinCompanyInfo(companyId);
    }

    public Long countReviewsByUserId(Long id) {
        log.info("기업 리뷰 작성 여부 확인 시작");

        return companyReviewJpaRepository.countByUserId(id);
    }

    @Transactional
    public void companyReviewInsert(CompanyReview saveReview) {

        log.info("기업 리뷰 등록 서비스 시작");

        companyReviewJpaRepository.save(saveReview);
    }

    @Transactional
    public void companyReviewDelete(Long reviewId) {
        log.info("기업 리뷰 삭제 서비스 시작");

        companyReviewJpaRepository.deleteById(reviewId);
    }

    // 기업 정보 등록여부 조회
    // 기업 등록 시 한 사람 당 하나의 기업 정보를 등록 할 수 있음
    public CompanyInfo findCompanyInfoByUserId(Long compUserId) {

        return companyJpaRepository.findCompanyInfoByCompUserId(compUserId);
    }
    */
}
