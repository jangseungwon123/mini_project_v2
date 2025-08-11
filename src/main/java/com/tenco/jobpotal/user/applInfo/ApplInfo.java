//package com.tenco.jobpotal.user.applInfo;
//import com.tenco.jobpotal._core.utils.MyDateUtil;
//import com.tenco.jobpotal.job_post.JobPost;
//import com.tenco.jobpotal.resume.Resume;
//import jakarta.persistence.*;
//import lombok.*;
//import org.hibernate.annotations.CreationTimestamp;
//
//import java.sql.Timestamp;
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Entity
//@Table(name = "appl_info")
//public class ApplInfo {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long applInfoId;
//
//    // 이력서
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "resume_id")
//    private Resume resume;
//
//    // 공고
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "jop_post_id")
//    private JobPost jobPost;
//
//
//
//    // 합/불 정보
//    @Column(nullable = false)
//    private String status = "대기";
//
//    // 지원 시간
//    @CreationTimestamp
//    private Timestamp createdAt;
//
//
//    @Transient
//    private boolean isApplInfoOwner;
//    public boolean isOwner(Long checkUserId){
//        return this.resume.getUser().getUserId().equals(checkUserId);
//    }
//
//    @Transient
//    private boolean isAccepted;
//    public boolean isAccepted() {
//        return "합격".equals(this.status);
//    }
//
//
//    public String getTime(){
//        return MyDateUtil.timestampFormat(createdAt);
//    }
//
//    public ApplInfo(Long applInfoId, Resume resume, JobPost jobPost, String status, Timestamp createdAt, boolean isApplInfoOwner, boolean isAccepted) {
//        this.applInfoId = applInfoId;
//        this.resume = resume;
//        this.jobPost = jobPost;
//        this.status = status;
//        this.createdAt = createdAt;
//        this.isApplInfoOwner = isApplInfoOwner;
//        this.isAccepted = isAccepted;
//    }
//}
