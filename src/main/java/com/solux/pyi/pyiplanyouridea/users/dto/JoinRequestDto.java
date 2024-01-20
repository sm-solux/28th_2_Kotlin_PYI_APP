package com.solux.pyi.pyiplanyouridea.users.dto;

import com.solux.pyi.pyiplanyouridea.users.domain.Users;
import com.solux.pyi.pyiplanyouridea.users.domain.UsersRole;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class JoinRequestDto {

    @NotBlank(message = "아이디")
    private String userId;

    @NotBlank(message = "비밀번호")
    private String password;
    //private String passwordCheck;

    @NotBlank(message = "이메일")
    private String userEmail;

    public Users toEntity(String encodedPassword) {
        return Users.builder()
                .userId(this.userId)
                .password(encodedPassword)
                .userEmail(this.userEmail)
                .userRole(UsersRole.ROLE_USER)
                .build();
    }

}
