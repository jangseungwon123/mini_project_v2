package com.tenco.jobpotal.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserJpaRepository userJpaRepository;

    // 회원가입
    @Transactional
    public UserResponse.JoinDTO join(UserRequest.JoinDTO joinDTO) {
        userJpaRepository.findByUserExists(joinDTO.getUserLoginId(), joinDTO.getUserCivilSerial(), joinDTO.getUserEmail())
                .ifPresent(user1 -> {
                    //  throw new Exception400("이미 존재하는 사용자입니다");
                });
        User savedUser = userJpaRepository.save(joinDTO.toEntity());
        return new UserResponse.JoinDTO(savedUser);
    }

//    // 로그인
//    public String login(UserRequest.LoginDTO loginDTO) {
//        User selectedUser = userJpaRepository
//                .findByUserIdAndPassword(loginDTO.getUserLoginId(), loginDTO.getUserPassword())
//                .orElseThrow(() -> {
//                    throw new Exception401("사용자명 또는 비밀번호가 틀렸어요");
//                });
//        String jwt = JwtUtil.create(selectedUser);
//        return jwt;
//    }
//
//    // 회원 정보 조회
//    public UserResponse.DetailDTO findById(Long requestUserId, Long sessionUserId) {
//        if (!requestUserId.equals(sessionUserId)) {
//            throw new Exception403("본인 정보만 조회 가능합니다");
//        }
//        User selectedUser = userJpaRepository.findById(requestUserId).orElseThrow(() -> {
//            throw new Exception404("사용자를 찾을 수 없습니다");
//        });
//        return new UserResponse.DetailDTO(selectedUser);
//    }
//
//    //회원 정보 수정
//    @Transactional
//    public UserResponse.UpdateDTO updateById(Long requestUserId, Long sessionUserId, UserRequest.UpdateDTO updateDTO) {
//        if (!requestUserId.equals(sessionUserId)) {
//            throw new Exception403("본인 정보만 조회 가능합니다");
//        }
//        User selectedUser = userJpaRepository.findById(requestUserId).orElseThrow(() -> {
//            throw new Exception404("사용자를 찾을 수 없습니다");
//        });
//        selectedUser.update(updateDTO);
//        return new UserResponse.UpdateDTO(selectedUser);
//    }
}