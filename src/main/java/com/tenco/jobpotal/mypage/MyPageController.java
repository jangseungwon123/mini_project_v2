package com.tenco.jobpotal.mypage;


import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/my-page")
@RequiredArgsConstructor
@Validated
public class MyPageController {
    private final MyPageService myPageService;


    //@GetMapping("/Summary")


}