package com.tenco.jobpotal.company;


import com.tenco.jobpotal._core.utils.MyDateUtil;
import com.tenco.jobpotal.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name = "comp_review")
public class CompReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private CompInfo companyInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 400)
    private String content;

    @Column(nullable = false)
    private boolean isCurrentEmployee;

    @Column(nullable = false)
    private boolean isRecommended;

    private String instId;
    @CreationTimestamp
    private Timestamp instDate;

    @Builder
    public CompReview(Long reviewId, CompInfo companyInfo, User user, String content, Boolean isCurrentEmployee, Boolean isRecommended, String instId, Timestamp instDate) {
        this.reviewId = reviewId;
        this.companyInfo = companyInfo;
        this.user = user;
        this.content = content;
        this.isCurrentEmployee = isCurrentEmployee;
        this.isRecommended = isRecommended;
        this.instId = instId;
        this.instDate = instDate;
    }

    @Transient
    private Boolean isMyReview;

    public boolean isOwner(Long sessionId) {
        return this.user.getUserId().equals(sessionId);
    }

    public String getTime() {
        return MyDateUtil.timestampFormat(instDate);
    }
}
