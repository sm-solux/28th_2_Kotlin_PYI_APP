package com.solux.pyi.pyiplanyouridea.users.dto;

import com.solux.pyi.pyiplanyouridea.users.domain.Users;
import lombok.Getter;

@Getter
public class LoginResponseDto {

    private String userId;
    private String password;

    public LoginResponseDto(Users entity) {
        this.userId = entity.getUserId();
        this.password = entity.getPassword();
    }

}
