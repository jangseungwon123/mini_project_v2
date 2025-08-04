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

    private String instId;
    @CreationTimestamp
    private Timestamp instDate;

    public FAQInfo(Long faqId, String title, String content, String instId, Timestamp instDate) {
        this.faqId = faqId;
        this.title = title;
        this.content = content;
        this.instId = instId;
        this.instDate = instDate;
    }
}
