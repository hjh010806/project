package com.example.Project.User;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPasswordForm {

    @NotEmpty(message = "현재 비밀번호 입력은 필수입니다.")
    private String password;
    @NotEmpty(message = "새 비밀번호 필수입니다.")
    private String newPassword1;

    @NotEmpty(message = "새 비밀번호 확인은 필수입니다.")
    private String newPassword2;
}
