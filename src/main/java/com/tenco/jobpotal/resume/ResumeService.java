package com.tenco.jobpotal.resume;


import com.tenco.jobpotal._core.errors.exception.Exception404;
import com.tenco.jobpotal.user.LoginUser;
import com.tenco.jobpotal.user.normal.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Transactional
    public ResumeResponse.UpdateDTO update(Long id,ResumeRequest.UpdateDTO updateDTO,
                                           LoginUser loginUser) {
        Resume resume = resumeJpaRepository.findByIdJoinUser(id).orElseThrow(() ->
                new Exception404("해당 이력서가 존재하지 않습니다"));
        resume.update(updateDTO);
        return new ResumeResponse.UpdateDTO(resume);
    }

    @Transactional
    public void deleteById(Long id,LoginUser loginUser) {
        Resume resume = resumeJpaRepository.findById(id).orElseThrow(() ->
                new Exception404("삭제 하려는 게시글이 없습니다"));
        resumeJpaRepository.deleteById(id);
    }

    public ResumeResponse.DetailDTO detail(Long id,LoginUser loginUser) {

        Resume resume = resumeJpaRepository.findByIdJoinUser(id).orElseThrow(
                () -> new Exception404("이력서를 찾을 수 없습니다"));
        return new ResumeResponse.DetailDTO(resume,loginUser);
    }

    public List<ResumeResponse.ResumeListResponseDTO> list(int page, int size) {
        Pageable pageable = PageRequest.of(page,size, Sort.by("id").descending());
        Page<Resume> resumePage = resumeJpaRepository.findAllJoinUser(pageable);
        List<ResumeResponse.ResumeListResponseDTO> resumeList = new ArrayList<>();
        for (Resume resume : resumePage.getContent()) {
            ResumeResponse.ResumeListResponseDTO resumeListResponseDTO = new ResumeResponse.ResumeListResponseDTO(resume);
            resumeList.add(resumeListResponseDTO);
        }
        return resumeList;
    }
}
