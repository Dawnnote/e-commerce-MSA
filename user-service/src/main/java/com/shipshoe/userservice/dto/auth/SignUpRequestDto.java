package com.shipshoe.userservice.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDto {

    @Email
    @NotBlank(message = "이메일은 필수 입력값입니다")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력값입니다")
    private String password;

    @NotBlank(message = "이름은 필수 입력값입니다")
    private String name;

    private String state;

    private String city;

    private String zipcode;

    private String street;

    @NotBlank(message = "전화번호는 필수 입력값입니다")
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "휴대폰 번호 양식에 맞지 않습니다.")
    private String phone;

    @NotBlank
    private String certificationNumber;

}
