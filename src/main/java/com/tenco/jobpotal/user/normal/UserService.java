package com.tenco.jobpotal.user.normal;

import com.tenco.jobpotal._core.errors.exception.Exception400;
import com.tenco.jobpotal._core.errors.exception.Exception401;
import com.tenco.jobpotal._core.errors.exception.Exception403;
import com.tenco.jobpotal._core.errors.exception.Exception404;
import com.tenco.jobpotal._core.utils.JwtUtil;
import com.tenco.jobpotal.user.LoginUser;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserJpaRepository userJpaRepository;
//    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Transactional
    public UserResponse.JoinDTO join(UserRequest.JoinDTO joinDTO) {
        userJpaRepository.findByUserExists(joinDTO.getUserLoginId(), joinDTO.getUserCivilSerial(), joinDTO.getUserEmail())
                .ifPresent(user1 -> {
                      throw new Exception400("이미 존재하는 사용자입니다");
                });

//        String hashedPassword = passwordEncoder.encode(joinDTO.getUserPassword());
//        joinDTO.setUserPassword(hashedPassword);

        User savedUser = userJpaRepository.save(joinDTO.toEntity());
        return new UserResponse.JoinDTO(savedUser);
    }

    // 로그인
    public String login(UserRequest.LoginDTO loginDTO) {
        User user = userJpaRepository
                .findByUserLoginIdAndUserPassword(loginDTO.getUserLoginId(), loginDTO.getUserPassword())
                .orElseThrow(() -> {
                    throw new Exception401("아이디 또는 비밀번호가 틀렸어요");
                });
//        if (!passwordEncoder.matches(loginDTO.getUserPassword(), user.getUserPassword())) {
//            throw new Exception401("아이디 또는 비밀번호가 틀렸어요");
//        }

        LoginUser loginUser = LoginUser.builder()
                .id(user.getUserId())
                .name(user.getUserName())
                .loginId(user.getUserLoginId())
                .userNickName(user.getUserNickname())
                .isCompany(false)
                .isAdmin(false)
                .build();
        String jwt = JwtUtil.create(loginUser);
        return jwt;
    }

    // 회원 정보 조회
    public UserResponse.DetailDTO findById( Long loginUserId) {
        User selectedUser = userJpaRepository.findById(loginUserId).orElseThrow(() -> {
            throw new Exception404("사용자를 찾을 수 없습니다");
        });
        return new UserResponse.DetailDTO(selectedUser);
    }

    //회원 정보 수정
    @Transactional
    public UserResponse.UpdateDTO updateById(Long requestUserId, Long loginUserId, UserRequest.UpdateDTO updateDTO) {
        if (!requestUserId.equals(loginUserId)) {
            throw new Exception403("본인 정보만 수정 가능합니다");
        }
        User selectedUser = userJpaRepository.findById(requestUserId).orElseThrow(() -> {
            throw new Exception404("사용자를 찾을 수 없습니다");
        });

//        String hashedPassword = passwordEncoder.encode(updateDTO.getUserPassword());
//        updateDTO.setUserPassword(hashedPassword);


        selectedUser.update(updateDTO);
        return new UserResponse.UpdateDTO(selectedUser);
    }

    // 유저가 관심 등록한 기업의 채용공고 리스트 스킬 매칭 조회
    public UserResponse.JobPostMatchListDTO jobMatchList(Long id) {


        return null;
    }


}