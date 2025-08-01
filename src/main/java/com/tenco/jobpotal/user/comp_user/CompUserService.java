package com.tenco.jobpotal.user.comp_user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CompUserService {
	private final CompUserJpaRepository compUserJpaRepository;

	@Transactional
	public CompUserResponse.JoinDTO join(CompUserRequest.JoinDTO joinDTO) {
//		compUserJpaRepository.findByCompUserLoginId(joinDTO.getCompUserLoginId())
//				.ifPresent(compUser -> {
//					throw new Exception("이미 존재하는 아이디입니다");
//				});
		compUserJpaRepository.findByCompUserLoginId(joinDTO.getCompUserLoginId());
		CompUser savedUser = compUserJpaRepository.save(joinDTO.toEntity());
		return new CompUserResponse.JoinDTO(savedUser);
	}




}
