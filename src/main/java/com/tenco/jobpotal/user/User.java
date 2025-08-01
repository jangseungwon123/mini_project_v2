package com.tenco.jobpotal.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "user_info")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String useName;

    @Column(unique = true, nullable = false)
    private String userLoginId;

    @Column(nullable = false)
    private String userPassword;

    @Column(unique = true, nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String userAddress;

    @Column(nullable = false)
    private String userPhone;

    @Column(nullable = false)
    private String userBirth;

    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private String userGender;

    @Column(nullable = false)
    private String userNickname;

    @Column(unique = true, nullable = false)
    private String userCivilSerial;

//    @OneToMany(mappedBy = "user")
//    private List<UserSkillList> userSkills;

    @Transient
    private Boolean isCompanyUserYn = false;

    @Builder
    public User(Long userId, String useName, String userLoginId, String userPassword, String userEmail, String userAddress, String userPhone, String userBirth, String userGender, String userNickname, String userCivilSerial) {
        this.userId = userId;
        this.useName = useName;
        this.userLoginId = userLoginId;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.userBirth = userBirth;
        this.userGender = userGender;
        this.userNickname = userNickname;
        this.userCivilSerial = userCivilSerial;
    }

    public void update() {
        this.useName = useName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.userNickname = userNickname;
    }
}
