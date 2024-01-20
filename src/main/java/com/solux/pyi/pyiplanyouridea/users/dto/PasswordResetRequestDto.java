package com.solux.pyi.pyiplanyouridea.users.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PasswordResetRequestDto {

    private String userId;
    private String newPassword;

}
