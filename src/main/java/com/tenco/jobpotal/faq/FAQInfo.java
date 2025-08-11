package com.tenco.jobpotal.faq;

import jakarta.persistence.*;
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

    private String instId; // 등록자
    @CreationTimestamp
    private Timestamp instDate; // 등록일

    public FAQInfo(Long faqId, String title, String content, String instId, Timestamp instDate) {
        this.faqId = faqId;
        this.title = title;
        this.content = content;
        this.instId = instId;
        this.instDate = instDate;
    }
}
