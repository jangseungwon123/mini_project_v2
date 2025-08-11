package com.tenco.jobpotal.mypage;

import com.tenco.jobpotal._core.errors.exception.Exception404;
import com.tenco.jobpotal.alarm.AlarmJpaRepository;
import com.tenco.jobpotal.community.compCommunity.CompCommunityRepository;
import com.tenco.jobpotal.community.userCommunity.UserCommunity;
import com.tenco.jobpotal.community.userCommunity.UserCommunityRepository;
import com.tenco.jobpotal.subscribe.UserSubJpaRepository;
import com.tenco.jobpotal.user.normal.UserJpaRepository;
import com.tenco.jobpotal.user.normal.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Summary = 요약
@Service
@RequiredArgsConstructor
@Transactional
public class MyPageService {

    UserSubJpaRepository userSubJpaRepository;
    UserJpaRepository userJpaRepository;
    CompCommunityRepository compCommunityRepository;
    UserCommunityRepository userCommunityRepository;
    AlarmJpaRepository alarmJpaRepository;

    // 내 정보 ->
    public UserRequest.MyProfileDTO getProfile(Long userId) {
        return userJpaRepository.findProfileDtoById(userId)
                .orElseThrow(() -> new Exception404("프로필 정보를 찾을 수 없습니다"));
    }

    // 내가 쓴 글 조회

    public Page<UserCommunity> myPost(Long userId, Pageable pageable) {
        return userCommunityRepository.findAllWithUserByUserId(userId,pageable);
    }

}
