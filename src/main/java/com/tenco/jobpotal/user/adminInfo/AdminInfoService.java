package com.tenco.jobpotal.user.adminInfo;

import com.tenco.jobpotal._core.errors.exception.Exception400;
import com.tenco.jobpotal._core.errors.exception.Exception401;
import com.tenco.jobpotal._core.errors.exception.Exception403;
import com.tenco.jobpotal._core.errors.exception.Exception404;
import com.tenco.jobpotal._core.utils.JwtUtil;
import com.tenco.jobpotal.user.LoginUser;
import com.tenco.jobpotal.user.normal.User;
import com.tenco.jobpotal.user.normal.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminInfoService {

     private final AdminInfoJpaRepository adminInfoJpaRepository;

     // 관리자 회원가입
    @Transactional
    public AdminInfoResponse.JoinDTO join(AdminInfoRequest.AdminJoinDTO joinDTO, LoginUser loginUser){
        // 동일한 아이디 있는지 확인(중복체크)
        if(loginUser == null){
            throw new Exception403("로그인 정보가 없습니다.");
        }
        adminInfoJpaRepository.findById(loginUser.getId()).orElseThrow(() ->
            new Exception403("관리자 생성 권한이 없습니다.")
        );
        adminInfoJpaRepository.findByAdminLoginId(joinDTO.getAdminLoginId())
                .ifPresent(adminInfo1 -> {
                    throw new Exception400("이미 존재하는 관리자ID 입니다.");
                });
        AdminInfo savedAdmin = adminInfoJpaRepository.save(joinDTO.toEntity());
        return new AdminInfoResponse.JoinDTO(savedAdmin);
    }

    // 로그인
    public String login(AdminInfoRequest.LoginDTO loginDTO){
        AdminInfo adminInfo = adminInfoJpaRepository
                .findByAdminLoginId(loginDTO.getAdminLoginId()).orElseThrow(() ->{
                    throw new Exception401("아이디 또는 비밀번호가 틀렸어요");
                        });
        LoginUser loginUser =LoginUser.builder()
                .id(adminInfo.getAdminId())
                .name(adminInfo.getAdminName())
                .loginId(adminInfo.getAdminLoginId())
                .isAdmin(true) // TODO 임시값 추후 role 부여 할 예정임
                .build();

        String jwt = JwtUtil.create(loginUser);
        return jwt;
    }
    // 내 정보 조회
    public AdminInfoResponse.DetailDTO findByMyId( LoginUser loginUser) {
        if(loginUser == null){
            throw new Exception403("로그인 정보가 없습니다.");
        }
        AdminInfo myId = adminInfoJpaRepository.findById(loginUser.getId()).orElseThrow(() -> {
            throw new Exception404("회원 정보를 찾을 수 없습니다");
        });
        return new AdminInfoResponse.DetailDTO(myId);
    }

    // (다른 )관리자 정보 상세 조회
    public AdminInfoResponse.DetailDTO findByTargetId( Long targetAdminId, LoginUser loginUser) {
        if(loginUser == null){
            throw new Exception403("로그인 정보가 없습니다.");
        }
        adminInfoJpaRepository.findById(loginUser.getId()).orElseThrow(() ->
                new Exception403("다른 관리자를 정보를 조회할 권한이 없습니다.")
        );
        AdminInfo selectedAdminInfoUser = adminInfoJpaRepository.findById(targetAdminId).orElseThrow(() -> {
            throw new Exception404("해당 관리자를 찾을 수 없습니다.");
        });
        return new AdminInfoResponse.DetailDTO(selectedAdminInfoUser);
    }


    // 관리자 정보 조회
    public AdminInfoResponse.UpdateDTO updateByAdmin(Long requestAdminId, Long loginAdminId, AdminInfoRequest.UpdateDTO updateDTO) {
        if (!requestAdminId.equals(loginAdminId)) {
            throw new Exception403("본인 정보만 수정 가능합니다");
        }
        AdminInfo selectedAdmin = adminInfoJpaRepository.findById(requestAdminId).orElseThrow(() -> {
            throw new Exception404("사용자를 찾을 수 없습니다") ;
        });
        selectedAdmin.update(updateDTO);
        return new AdminInfoResponse.UpdateDTO(selectedAdmin);
    }
}
