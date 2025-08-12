package com.tenco.jobpotal.faq;

import com.tenco.jobpotal.faq.FAQRequestDTO;
import com.tenco.jobpotal.faq.FAQResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FAQService {

    private final FAQJpaRepository faqRepository;

    // 전체 목록
    public List<FAQResponseDTO> getAllFAQs() {
        return faqRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // 상세 조회
    public FAQResponseDTO getFAQ(Long id) {
        FAQInfo faq = faqRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FAQ not found"));
        return toDTO(faq);
    }

    // 등록
    @Transactional
    public FAQResponseDTO createFAQ(FAQRequestDTO dto) {
        FAQInfo faq = FAQInfo.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
//                .instId(instId)
                .build();

        return toDTO(faqRepository.save(faq));
    }

    // 수정
    public FAQResponseDTO updateFAQ(Long id, FAQRequestDTO dto) {
        FAQInfo faq = faqRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FAQ not found"));
        faq.setTitle(dto.getTitle());
        faq.setContent(dto.getContent());
        return toDTO(faqRepository.save(faq));
    }

    // 삭제
    public void deleteFAQ(Long id) {
        if (!faqRepository.existsById(id)) {
            throw new RuntimeException("FAQ not found");
        }
        faqRepository.deleteById(id);
    }

    // Entity → DTO 변환
    private FAQResponseDTO toDTO(FAQInfo faq) {
        FAQResponseDTO dto = new FAQResponseDTO();
        dto.setFaqId(faq.getFaqId());
        dto.setTitle(faq.getTitle());
        dto.setContent(faq.getContent());
//        dto.setInstId(faq.getInstId());
//        dto.setInstDate(faq.getInstDate() != null ? faq.getInstDate().toString() : null);
        return dto;
    }
}
