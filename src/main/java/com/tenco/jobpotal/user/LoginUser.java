package com.tenco.jobpotal.user;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginUser {

    private Long id;
    private String name;
    private String loginId;
    private String userNickName;
    private boolean isCompany;

    @Builder
    public LoginUser(Long id, String name, String loginId, String userNickName, boolean isCompany) {
        this.id = id;
        this.name = name;
        this.loginId = loginId;
        this.userNickName = userNickName;
        this.isCompany = isCompany;
    }
}
