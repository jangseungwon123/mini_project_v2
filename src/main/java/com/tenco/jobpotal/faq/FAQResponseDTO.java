package com.tenco.jobpotal.faq;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class FAQResponseDTO {
    private Long faqId;
    private String title;
    private String content;
//    private String instId;
//    private String instDate;
}


