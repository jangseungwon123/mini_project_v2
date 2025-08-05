package com.tenco.jobpotal.reply.job_reply;

import com.tenco.jobpotal._core.errors.exception.Exception403;
import com.tenco.jobpotal._core.errors.exception.Exception404;
import com.tenco.jobpotal.community.community1.Community;
import com.tenco.jobpotal.community.community1.CommunityRepository;
import com.tenco.jobpotal.user.LoginUser;
import com.tenco.jobpotal.user.normal.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service

public class JobCommCmtService {

    private final JobCommCmtJPARepository jobCommCmtJPARepository;
    private final CommunityRepository communityRepository;

    @Transactional
    public JobCommCmtResponse.SaveDTO save(JobCommCmtRequest.SaveDTO saveDTO, LoginUser loginUser) {

        Community community = communityRepository.findById(saveDTO.getPostId())
                .orElseThrow(() -> new Exception404("존재하지 않는 게시글에는 댓글을 작성할 수 없습니다."));

        User user = User.builder()
                .userId(loginUser.getId())
                .userName(loginUser.getName())
                .userEmail(loginUser.getUserNickName())
                .build();

        JobCommCmt jobCommCmt = saveDTO.toEntity(user, community);
        jobCommCmtJPARepository.save(jobCommCmt);

        return new JobCommCmtResponse.SaveDTO(jobCommCmt);
    }

    @Transactional
    public void deleteById(Long jobCommCmtId, LoginUser loginUser) {

        JobCommCmt jobCommCmt = jobCommCmtJPARepository.findById(jobCommCmtId)
                .orElseThrow(() -> new Exception404("삭제할 댓글이 없어요"));

        if (!jobCommCmt.isOwner(loginUser.getId())) {
            throw new Exception403("본인이 작성한 댓글만 삭제 할 수 있음");
        }

        jobCommCmtJPARepository.deleteById(jobCommCmtId);
    }
}
