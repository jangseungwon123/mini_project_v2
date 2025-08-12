package com.tenco.jobpotal.mypage;


import com.tenco.jobpotal._core.utils.Define;
import com.tenco.jobpotal.community.userCommunity.UserCommunity;
import com.tenco.jobpotal.community.userCommunity.UserCommunityResponse;
import com.tenco.jobpotal.user.LoginUser;
import com.tenco.jobpotal.user.normal.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/my-page")
@RequiredArgsConstructor
@Validated
public class MyPageController {
    private final UserJpaRepository userJpaRepository;
    private final MyPageService myPageService;

    // 마이페이지 - 내가 쓴 글 조회하기
    @GetMapping("/posts")
    public ResponseEntity<Page<UserCommunityResponse.MyPostResponse>> myPosts(
            @RequestAttribute(value = Define.LOGIN_USER, required = false) LoginUser loginUser, Pageable pageable) {
        Page<UserCommunityResponse.MyPostResponse> response = myPageService.myPosts(loginUser, pageable);
        return ResponseEntity.ok(response); //
    }

}