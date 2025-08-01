package com.tenco.jobpotal.resume;


import com.tenco.jobpotal.user.LoginUser;
import com.tenco.jobpotal.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ResumeService {

    private final ResumeJpaRepository resumeJpaRepository;

    @Transactional
    public ResumeResponse.SaveDTO save(ResumeRequest.SaveDTO saveDTO, LoginUser loginUser){
        User user = User.builder()
                .userId(loginUser.getId())
                .userName(loginUser.getName())
                .userNickname(loginUser.getUserNickName())
                .build();
        Resume resume = saveDTO.toEntity(user);
        Resume saveResume = resumeJpaRepository.save(resume);
        return new ResumeResponse.SaveDTO(saveResume);
    }
}
