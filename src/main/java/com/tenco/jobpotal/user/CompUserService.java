package com.tenco.jobpotal.user;

import com.tenco.jobpotal._core.errors.exception.Exception400;
import com.tenco.jobpotal._core.errors.exception.Exception403;
import com.tenco.jobpotal._core.errors.exception.Exception404;
import com.tenco.jobpotal._core.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CompUserService {
	private final CompUserJpaRepository compUserJpaRepository;

	// 회원가입
	@Transactional
	public CompUserResponse.JoinDTO join(CompUserRequest.JoinDTO joinDTO) {
		compUserJpaRepository.findByCompUserExists(joinDTO.getCompUserLoginId(), joinDTO.getCompUserEmail())
				.ifPresent(compUser -> {
					throw new Exception400("이미 존재하는 아이디입니다");
				});
		compUserJpaRepository.findByCompUserExists(joinDTO.getCompUserLoginId(), joinDTO.getCompUserEmail());
		CompUser savedUser = compUserJpaRepository.save(joinDTO.toEntity());
		return new CompUserResponse.JoinDTO(savedUser);
	}

//	// 로그인
//	public String login(CompUserRequest.LoginDTO loginDTO) {
//		LoginUser selectedCompUser = compUserJpaRepository
//				.findByCompUserLoginIdAndCompUserPassword(
//						loginDTO.getCompUserLoginId(), loginDTO.getCompUserPassword())
//				.orElseThrow(RuntimeException::new);
//		String jwt = JwtUtil.create(selectedCompUser);
//		return jwt;
//	}

	// 회원정보조회
	public CompUserResponse.DetailDTO findCompUserByCompUserId(
			Long requestCompUserId, Long sessionCompUserId) {
		if (!requestCompUserId.equals(sessionCompUserId)) {
			throw new Exception403("접근권한이 없습니다");
		}
		CompUser selectedCompUser = compUserJpaRepository.findById(requestCompUserId)
				.orElseThrow(() -> new Exception404("존재하지 않는 회원입니다"));
		return new CompUserResponse.DetailDTO(selectedCompUser);
	}

	// 회원정보수정
	@Transactional
	public CompUserResponse.UpdateDTO updateById(
			Long requestCompUserId, Long sessionCompUserId, CompUserRequest.UpdateDTO updateDTO) {
		if (!requestCompUserId.equals(sessionCompUserId)) {
			throw new Exception403("접근권한이 없습니다");
		}
		CompUser selectedCompUser = compUserJpaRepository.findById(requestCompUserId)
				.orElseThrow(() -> new Exception404("존재하지 않는 회원입니다"));
		selectedCompUser.update(updateDTO);
		return new CompUserResponse.UpdateDTO(selectedCompUser);
	}
}
