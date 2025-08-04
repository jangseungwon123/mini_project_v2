package com.tenco.jobpotal.company.comp_rating;

import com.tenco.jobpotal.user.appl_info.AppiInfo;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "comp_rating")
public class CompRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private AppiInfo applInfoId;

    @Column(nullable = false)
    private int score;

    @Builder
    public CompRating(CompRating compRating) {
        this.ratingId = compRating.ratingId;
        this.applInfoId = compRating.applInfoId;
        this.score = compRating.score;
    }
}
