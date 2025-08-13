package com.tenco.jobpotal.resume;


import com.tenco.jobpotal._core.errors.exception.Exception404;
import com.tenco.jobpotal.skill.SkillList;
import com.tenco.jobpotal.user.LoginUser;
import com.tenco.jobpotal.user.normal.User;
import com.tenco.jobpotal.user.normal.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ResumeService {

    private final ResumeJpaRepository resumeJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final SkillListJpaRepository skillListJpaRepository;
    private final UserSkillListRepository userSkillListRepository;

//    @Transactional
//    public void save(ResumeRequest.SaveDTO saveDTO,LoginUser loginUser){
//        // 1. 사용자 인증
//        User user = userJpaRepository.findById(loginUser.getId())
//                .orElseThrow(() -> new Exception404("사용자를 찾을 수 없습니다"));
//
//        // 2. 이력서 본문 저장
//        Resume resume = resumeJpaRepository.save(saveDTO.toEntity(user));
//
//        // 3. 스킬 리스트 처리
//        if (saveDTO.getSkillId() != null && !saveDTO.getSkillId().isEmpty()) {
//            // 3.1. 요청된 skillId에 해당하는 SkillList 엔티티들을 한 번에 조회
//            List<SkillList> skills = skillListJpaRepository.findAllBySkillIdIn(saveDTO.getSkillId());
//            if (skills.size() != saveDTO.getSkillId().size()) {
//                throw new Exception404("존재하지 않는 스킬이 포함되어 있습니다.");
//            }
//
//            List<UserSkillList> userSkillLists = skills.stream().map(skill ->
//                    UserSkillList.builder()
//                            .user(user)
//                            .resume(resume)
//                            .skillList(skill)
//                            .instId(loginUser.getName()) // DTO의 name 대신 로그인 유저 이름 사용 권장
//                            .build()
//            ).collect(Collectors.toList());
//
//            userSkillListRepository.saveAll(userSkillLists);
//        }
//    }

    @Transactional
    public ResumeResponse.UpdateDTO update(Long id, ResumeRequest.UpdateDTO updateDTO,
                                           LoginUser loginUser) {

        Resume resume = resumeJpaRepository.findByIdJoinUser(id).orElseThrow(() ->
                new Exception404("해당 이력서가 존재하지 않습니다"));

        SkillList skillList = skillListJpaRepository.findBySkillId(updateDTO.getSkillId())
                .orElseThrow(() -> new Exception404("스킬을 찾을 수 없습니다."));

        resume.update(updateDTO);

        return new ResumeResponse.UpdateDTO(resume);
    }

    @Transactional
    public void deleteById(Long id, LoginUser loginUser) {
        Resume resume = resumeJpaRepository.findById(id).orElseThrow(() ->
                new Exception404("삭제 하려는 게시글이 없습니다"));
        resumeJpaRepository.deleteById(id);
    }

    public ResumeResponse.DetailDTO detail(Long id, LoginUser loginUser) {

        Resume resume = resumeJpaRepository.findByIdJoinUser(id).orElseThrow(
                () -> new Exception404("이력서를 찾을 수 없습니다"));
        return new ResumeResponse.DetailDTO(resume, loginUser);
    }

    public List<ResumeResponse.ResumeListResponseDTO> list(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Resume> resumePage = resumeJpaRepository.findAllJoinUser(pageable);
        List<ResumeResponse.ResumeListResponseDTO> resumeList = new ArrayList<>();
        for (Resume resume : resumePage.getContent()) {
            ResumeResponse.ResumeListResponseDTO resumeListResponseDTO = new ResumeResponse.ResumeListResponseDTO(resume);
            resumeList.add(resumeListResponseDTO);
        }
        return resumeList;
    }
}
