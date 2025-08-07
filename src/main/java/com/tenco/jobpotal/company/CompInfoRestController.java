package com.tenco.jobpotal.company;

import com.tenco.jobpotal._core.common.ApiUtil;
import com.tenco.jobpotal._core.errors.exception.Exception500;
import com.tenco.jobpotal._core.utils.Define;
import com.tenco.jobpotal.user.*;
import com.tenco.jobpotal.user.comp.CompUser;
import com.tenco.jobpotal.user.comp.CompUserService;
import com.tenco.jobpotal.user.normal.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CompInfoRestController {


    private static final Logger log = LoggerFactory.getLogger(CompInfoRestController.class);
    private final UserService userService;
    private final CompInfoService companyService;
    private final CompUserService compUserService;
    

    //전체 게시글 조회 and 제목 검색한 게시글 조회
    @GetMapping("/company/list")
    public ResponseEntity<?> companyInfoList(
                                  @RequestParam(required = false) String type, // todo 검색 시 회사 종류도 선택해서 처리 추가할지 고민필요
                                  @RequestParam(required = false) String keyword,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {

        List<CompInfoResponse.MainDTO> compInfoPage;

        if (keyword != null && !keyword.trim().isEmpty()) {
            log.info("keyword 값 확인 : {}", keyword);

            compInfoPage = companyService.findAllCompanyInfo(page, size, keyword);

            return ResponseEntity.ok(new ApiUtil<>(compInfoPage));
        } else {
            compInfoPage = companyService.findAllCompanyInfo(page, size, keyword);
            return ResponseEntity.ok(new ApiUtil<>(compInfoPage));
        }
    }

    // 기업정보 상세
    @GetMapping("/company/{id}")
    public ResponseEntity<?> companyInfoDetail(@PathVariable(name = "id") Long id,
                                               @RequestAttribute(value = Define.LOGIN_USER, required = false) LoginUser loginUser) {

        log.info(">> 기업 상세정보 조회 시작 << ");


        CompInfoResponse.DetailDTO companyInfoDetail = companyService.findCompanyInfoById(id, loginUser);

        return ResponseEntity.ok(new ApiUtil<>(companyInfoDetail));
    }

    // 기업정보 등록
    @PostMapping("/company/form")
    public ResponseEntity<?> companyInfoInsert(@RequestBody CompInfoRequest.SaveDTO saveDTO,
                                    @RequestAttribute(value = Define.LOGIN_USER, required = false) LoginUser loginUser) {

        log.info(">> 기업정보 등록 시작 << ");
        log.info(">> jwt 유저 정보 확인 : {} << ", loginUser);

        // 존재하는 유저인지 확인
        CompUser compUser = compUserService.findCompUserByCompUserId(loginUser.getId()).toEntity();

        // 등록 처리
        CompInfo companyInfo = companyService.companyInfoInsert(compUser, saveDTO);

        if (companyInfo == null) {
            throw new Exception500("등록 처리 중 에러가 발생했습니다. 관리자에게 문의 하세요.");
        }

        return ResponseEntity.ok(new ApiUtil<>("기업 등록이 완료 되었습니다."));
    }

    // 기업정보 수정
    @PutMapping("/company/{id}/update")
    public ResponseEntity<?> companyInfoUpdate(@PathVariable(name = "id") Long id,
                                               @RequestBody CompInfoRequest.UpdateDTO updateDTO,
                                               @RequestAttribute(value = Define.LOGIN_USER, required = false) LoginUser loginUser) {

        log.info(">> 기업정보 수정 시작 << ");

        CompInfo companyInfo = companyService.companyInfoUpdate(id, updateDTO, loginUser);

        if (companyInfo == null) {
            throw new Exception500("수정 처리 중 에러가 발생했습니다.");
        }

        return ResponseEntity.ok(new ApiUtil<>("기업정보 수정이 완료 되었습니다."));
    }

    @DeleteMapping("/company/{id}/delete")
    public ResponseEntity<?> companyInfoDelete(@PathVariable(name = "id") Long id,
                                               @RequestAttribute(value = Define.LOGIN_USER, required = false) LoginUser loginUser) {

        log.info(">> 기업정보 삭제 시작 << ");

        companyService.companyInfoDelete(loginUser, id);
        return ResponseEntity.ok(new ApiUtil<>("기업 정보 삭제를 완료 하었습니다."));
    }

    /*
    // 전체 리뷰 목록
    @GetMapping("/company/{id}/reviews")
    public String companyReview(@PathVariable(name = "id") Long companyId,
                                @RequestParam(defaultValue = "0")int page,
                                @RequestParam(defaultValue = "10")int size, Model model, HttpSession session)  {

        LoginUser user = (LoginUser)session.getAttribute("sessionUser");
        Pageable pageable = PageRequest.of(page ,size, Sort.by("instDate").descending());

        Page<CompanyReview> companyReviews = companyService.findCompanyReviewByCompanyId(pageable,companyId);

        // 페이지 네비게이션 용 데이터 준비
        List<PageLink> pageLinks = new ArrayList<>();
        for(int i = 0; i < companyReviews.getTotalPages(); i++) {
            pageLinks.add(new PageLink(i, i + 1, i == companyReviews.getNumber()));
        }

        Integer previousPageNumber = companyReviews.hasPrevious() ? companyReviews.getNumber() -1 : null;
        Integer nextPageNumber = companyReviews.hasNext() ? companyReviews.getNumber() + 1 : null;

        // 뷰 화면에 데이터 전달
        model.addAttribute("companyReviews", companyReviews);
        // 페이지 네비게이션에 사용할 번호 링크 리스트
        model.addAttribute("pageLinks", pageLinks);
        // 이전 페이지 번호 (없으면 null)
        model.addAttribute("previousPageNumber", previousPageNumber);
        // 다음 페이지 번호 (없으면 null)
        model.addAttribute("nextPageNumber", nextPageNumber);
        // 리뷰 소유권 설정 (삭제 버튼 표시용)
        if (user != null) {
            if (!user.isCompany()) {
                companyReviews.forEach(companyReview -> {
                    boolean isReviewOwner = companyReview.isOwner(user.getId());
                    companyReview.setIsMyReview(isReviewOwner);
                    log.info("isMyReview 확인 : {}", companyReview.getIsMyReview());
                });

                // 이전에 리뷰 작성을 하였는지 확인하여 작성 했을 시 등록 버튼 숨김
                Long findReviewById = companyService.countReviewsByUserId(user.getId());
                boolean isWritable = (findReviewById == null || findReviewById <= 0);

                model.addAttribute("isWritable", isWritable);
            }
        }

        log.info("리뷰 개수 확인 : {}", companyReviews.getSize());

        model.addAttribute("companyId", companyId);
        model.addAttribute("reviews", companyReviews);

        return "company/company_reviews";
    }

    @GetMapping("/company/{id}/reviews/form")
    public String companyReviewForm(@PathVariable(name = "id") Long companyId, Model model) {

        model.addAttribute("companyId", companyId);

        return "company/company_review_form";
    }

    @PostMapping("/company/{id}/reviews/form")
    public String companyReviewFormInsert(@PathVariable(name = "id") Long companyId, Model model, CompanyRequest.SaveReviewDTO saveReviewDTO, HttpSession session) {

        // String -> boolean 변환 처리를 위한 설정
        saveReviewDTO.setCurrentEmployee(saveReviewDTO.getIsCurrentEmployeeYn().equals("true"));
        saveReviewDTO.setRecommended(saveReviewDTO.getIsRecommendedYn().equals("true"));

        CompanyInfo companyInfo = companyService.findCompanyInfoById(companyId);

        // 임시 테스트를 위한 세션유저 세팅
        LoginUser sessionUser = (LoginUser)session.getAttribute("sessionUser");
        User user = userService.findById(sessionUser.getId());

        if (user == null) {
            throw new Exception404("유저 정보를 찾을 수 없습니다.");
        }

        // 한 유저는 기업에 리뷰를 하나만 달 수 있다.
        // 이미 등록되어 있는 리뷰가 있을 시 등록불가
        List<CompanyReview> companyReviews = companyService.findCompanyReviewByCompanyId(companyId);

        companyReviews.forEach(companyReview -> {
            boolean isReviewOwner = companyReview.isOwner(user.getUserId());
            if (isReviewOwner) {
                throw new Exception400("계정 하나 당 하나의 기업 리뷰를 등록 할 수 있습니다.");
            }
        });

        CompanyReview saveReview = saveReviewDTO.toEntity(user, companyInfo);
        companyService.companyReviewInsert(saveReview);

        model.addAttribute("companyId", companyId);

        return "redirect:/company/"+companyId+"/reviews";
    }

    @PostMapping("/company/{companyId}/reviews/{reviewId}/delete")
    public String companyReviewDelete(@PathVariable(name = "companyId")Long companyId,
                                      @PathVariable(name = "reviewId")Long reviewId)
    {

        companyService.companyReviewDelete(reviewId);

        return "redirect:/company/"+ companyId +"/reviews";
    }
    */
}
