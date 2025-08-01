package com.tenco.jobpotal.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserRestController {

    private final UserService userService;

//    public ResponseEntity<?> join(@Valid @RequestBody UserRequest.JoinDTO joinDTO, Errors errors) {
//        UserResponse.JoinDTO joinUser = userService.join(joinDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiUtil<>(joinUser));
//    }

}

