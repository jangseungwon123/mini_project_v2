package com.tenco.jobpotal.report;

import com.tenco.jobpotal._core.errors.exception.Exception403;
import com.tenco.jobpotal._core.errors.exception.Exception404;
import com.tenco.jobpotal._core.errors.exception.Exception500;
import com.tenco.jobpotal.community.userCommunity.UserCommunity;
import com.tenco.jobpotal.community.userCommunity.UserCommunityRepository;
import com.tenco.jobpotal.user.normal.User;
import com.tenco.jobpotal.user.normal.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReportService {

	private final ReportJpaRepository reportJpaRepository;
	private final UserJpaRepository userJpaRepository;
	private final UserCommunityRepository userCommunityRepository;

	// 신고하기 기능
	@Transactional
	public ReportResponse.SaveDTO createReport(ReportRequest.CreateDTO createDTO, Long userId) {
		reportJpaRepository.findByUser_UserIdAndUserCommunity_PostId(userId, createDTO.getPostId())
				.ifPresent(report -> { throw new Exception500("이미 신고한게실글입니다.");
				});

		User user = userJpaRepository.findById(userId)
				.orElseThrow(() -> {throw new Exception404("존재하지 않는 유저입니다.");});
		UserCommunity userCommunity = userCommunityRepository.findById(createDTO.getPostId())
				.orElseThrow(() -> {throw new Exception404("존재하지 않는 게시글입니다.");});

		Report report = Report.builder()
				.user(user)
				.userCommunity(userCommunity)
				.build();

		Report savedReport = reportJpaRepository.save(report);

		return new ReportResponse.SaveDTO(savedReport);

	}
}
