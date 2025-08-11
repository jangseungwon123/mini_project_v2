package com.tenco.jobpotal.user.normal;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "user_info")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String userName;

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


    @Column(nullable = false)
    private String userNickname;

    @Column(unique = true, nullable = false)
    private String userCivilSerial;

    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private String userGender;

//    @OneToMany(mappedBy = "user")
//    private List<UserSkillList> userSkills;

    @Transient
    private Boolean isCompanyUserYn = false;

    @Builder
    public User(Boolean isCompanyUserYn, Long userId, String userName, String userLoginId, String userPassword, String userEmail, String userAddress, String userPhone, String userBirth, String userGender, String userNickname, String userCivilSerial) {
        this.userId = userId;
        this.userName = userName;
        this.userLoginId = userLoginId;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.userBirth = userBirth;
        this.userGender = userGender;
        this.userNickname = userNickname;
        this.userCivilSerial = userCivilSerial;
        this.isCompanyUserYn = isCompanyUserYn;
    }

    public void update(UserRequest.UpdateDTO updateDTO) {
        this.userName = updateDTO.getUserName();
        this.userPassword = updateDTO.getUserPassword();
        this.userEmail = updateDTO.getUserEmail();
        this.userAddress = updateDTO.getUserAddress();
        this.userPhone = updateDTO.getUserPhone();
        this.userNickname = updateDTO.getUserNickname();
    }
}
