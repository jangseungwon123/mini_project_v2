package com.tenco.jobpotal.faq;
import com.tenco.jobpotal._core.errors.exception.Exception404;
import com.tenco.jobpotal.user.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class FAQService {
    private final FAQJpaRepository faqRepository;

    // FAQ 등록
    @Transactional
    public FAQResponseDTO create(FAQRequestDTO dto, LoginUser loginUser) {
        FAQInfo entity = new FAQInfo();
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setInstId(loginUser.getLoginId()); // [보안] 서버에서 로그인한 사용자의 ID를 설정

        FAQInfo saved = faqRepository.save(entity);

        return toDTO(saved);
    }

    // 모든 FAQ 목록 조회
    public List<FAQResponseDTO> getAll() {
        return faqRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // 특정 FAQ 1개 조회
    public FAQResponseDTO getById(Long faqId) {
        FAQInfo entity = faqRepository.findById(faqId)
                .orElseThrow(() -> new Exception404("해당 FAQ를 찾을 수 없습니다."));
        return toDTO(entity);
    }


    // FAQInfo 를 FAQResponseDTO로 바꾸는 함수
    private FAQResponseDTO toDTO(FAQInfo entity) {
        FAQResponseDTO dto = new FAQResponseDTO();
        dto.setFaqId(entity.getFaqId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setInstId(entity.getInstId());
        dto.setInstDate(entity.getInstDate());
        return dto;
    }

    @Transactional
    public FAQResponseDTO update(Long faqId, FAQRequestDTO dto) {
        FAQInfo entity = faqRepository.findById(faqId)
                .orElseThrow(() -> new Exception404("해당 FAQ를 찾을 수 없습니다."));
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        // 등록자는 수정하지 않는다고 가정

        return toDTO(entity);
    }

    @Transactional
    public void delete(Long faqId) {
        if (!faqRepository.existsById(faqId)) {
            throw new Exception404("해당 FAQ를 찾을 수 없습니다.");
        }
        faqRepository.deleteById(faqId);
    }

}
