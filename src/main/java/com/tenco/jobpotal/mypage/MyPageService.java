package com.tenco.jobpotal.mypage;

import com.tenco.jobpotal._core.errors.exception.Exception404;
import com.tenco.jobpotal.alarm.AlarmJpaRepository;
import com.tenco.jobpotal.community.compCommunity.CompCommunityRepository;
import com.tenco.jobpotal.community.userCommunity.UserCommunity;
import com.tenco.jobpotal.community.userCommunity.UserCommunityRepository;
import com.tenco.jobpotal.community.userCommunity.UserCommunityResponse;
import com.tenco.jobpotal.subscribe.UserSubJpaRepository;
import com.tenco.jobpotal.user.LoginUser;
import com.tenco.jobpotal.user.normal.UserJpaRepository;
import com.tenco.jobpotal.user.normal.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

// Summary = 요약
@Service
@RequiredArgsConstructor
public class MyPageService {

    UserSubJpaRepository userSubJpaRepository;
    private final UserJpaRepository userJpaRepository;
    CompCommunityRepository compCommunityRepository;
    private final UserCommunityRepository userCommunityRepository;
    AlarmJpaRepository alarmJpaRepository;

    // 내 정보 ->
    public UserRequest.MyProfileDTO getProfile(Long userId) {
        return userJpaRepository.findProfileDtoById(userId)
                .orElseThrow(() -> new Exception404("프로필 정보를 찾을 수 없습니다"));
    }


    // 내가 쓴 글 조회-필요한것 : 나[세션]의 id , usercommunity에 따로 쿼리 만들기

    public Page<UserCommunityResponse.MyPostResponse> myPosts(LoginUser loginUser, Pageable pageable) {
        Page<UserCommunity> userCommunities =
                userCommunityRepository.findAllWithUserByUserId(loginUser.getId(), pageable);

        return userCommunities.map(UserCommunityResponse.MyPostResponse::fromEntity);
    }

}
