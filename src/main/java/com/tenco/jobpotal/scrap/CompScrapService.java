package com.tenco.jobpotal.scrap;

import com.tenco.jobpotal._core.errors.exception.Exception403;
import com.tenco.jobpotal._core.errors.exception.Exception404;
import com.tenco.jobpotal.company.CompInfo;
import com.tenco.jobpotal.company.CompInfoJpaRepository;
import com.tenco.jobpotal.user.LoginUser;
import com.tenco.jobpotal.user.normal.User;
import com.tenco.jobpotal.user.normal.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CompScrapService {
    private final CompScrapJpaRepository compScrapJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final CompInfoJpaRepository compInfoJpaRepository;

    // 구독
    @Transactional
    public CompScrapResponse.SaveDTO save(CompScrapRequest.SaveDTO saveDTO, LoginUser loginUser) {
        if (compScrapJpaRepository.existsByCompIdAndUserId(saveDTO.getCompId(), loginUser.getId())) {
            throw new Exception403("이미 구독했습니다");
        }
        User user = userJpaRepository.findById(loginUser.getId()).orElseThrow(() ->
                new Exception404("존재하지 않는 사용자입니다"));
        CompInfo compInfo = compInfoJpaRepository.findById(saveDTO.getCompId()).orElseThrow(() ->
                new Exception404("존재하지 않는 기업입니다."));

        CompScrap compScrap = CompScrap.builder()
                .user(user)
                .compInfo(compInfo)
                .build();


        CompScrap savedCompScrap = compScrapJpaRepository.save(compScrap);
        return new CompScrapResponse.SaveDTO(savedCompScrap);
    }

    // 구독목록 조회 서비스
    public List<CompScrapResponse.SubListDTO> findAllByUserAndCompanyId(Long userId) {
        List<CompScrapResponse.SubListDTO> userSubList = compScrapJpaRepository.findAllByUserAndCompId(userId);
        return userSubList;
    }

    // 구독 삭제
    @Transactional
    public void deleteById(Long userSubId, LoginUser loginUser) {
        CompScrap compScrap = compScrapJpaRepository.findById(userSubId).orElseThrow(() ->
                new Exception404("삭제하려는 구독이 없습니다"));
        if (!compScrap.isOwner(loginUser.getId())) {
            throw new Exception403("본인의 구독만 취소할 수 있습니다");
        }
        compScrapJpaRepository.deleteById(userSubId);
    }
}
