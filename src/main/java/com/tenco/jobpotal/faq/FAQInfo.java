package com.tenco.jobpotal.faq;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name = "faq_info")
public class FAQInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long faqId;

    private String title;
    private String content;

//    private String instId; // 등록자
//    @CreationTimestamp
//    private Timestamp instDate; // 등록일

    @Builder
    public FAQInfo(Long faqId, String title, String content) {
        this.faqId = faqId;
        this.title = title;
        this.content = content;
//        this.instId = instId;
//        this.instDate = instDate;
    }
}
