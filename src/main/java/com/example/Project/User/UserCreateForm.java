package com.example.Project.User;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
    @NotEmpty(message = "이름은 필수입니다.")
    private String name;
    @Size(min = 3, max = 25)
    @NotEmpty(message = "닉네임은 필수입니다.")
    private String nickName;

    @NotEmpty(message = "비밀번호 필수입니다.")
    private String password1;

    @NotEmpty(message = "비밀번호 확인은 필수입니다.")
    private String password2;

    @NotEmpty(message = "번호는 필수입니다.")
    private String number;

    @NotEmpty(message = "이메일은 필수입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

}
