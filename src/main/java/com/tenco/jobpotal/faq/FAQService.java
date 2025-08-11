package com.tenco.jobpotal.faq;
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
    public FAQResponseDTO create(FAQRequestDTO dto) {
        FAQInfo entity = new FAQInfo();
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setInstId(dto.getInstId());

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
                .orElseThrow(() -> new IllegalArgumentException("FAQ Not Found"));
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
                .orElseThrow(() -> new IllegalArgumentException("FAQ Not Found"));

        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        // 등록자는 수정하지 않는다고 가정

        return toDTO(entity);
    }

    @Transactional
    public void delete(Long faqId) {
        if (!faqRepository.existsById(faqId)) {
            throw new IllegalArgumentException("FAQ Not Found");
        }
        faqRepository.deleteById(faqId);
    }

}
