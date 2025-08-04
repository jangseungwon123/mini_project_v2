package com.tenco.jobpotal.company.compSub;

import com.tenco.jobpotal._core.utils.MyDateUtil;
import com.tenco.jobpotal.user.comp.CompUser;
import com.tenco.jobpotal.user.normal.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table
@Entity
public class CompSub {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comp_info_id")
    private CompUser compUser;

    @CreationTimestamp
    private Timestamp compSubDate;

    public boolean isOwner(Long checkUserId){return this.compUser.getCompUserId().equals(checkUserId);}

    public String getTime() {return MyDateUtil.timestampFormat(compSubDate);}
}
