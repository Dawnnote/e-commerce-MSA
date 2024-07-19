package com.shipshoe.userservice.dto.auth;

import com.shipshoe.userservice.common.ResponseCode;
import com.shipshoe.userservice.common.ResponseMessage;
import com.shipshoe.userservice.dto.UserResponseValidationDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class SignInResponseDto extends UserResponseValidationDto {

    private String token;
    private int expirationTime;

    private SignInResponseDto (String token) {
        super();
        this.token = token;
        this.expirationTime = 3600;
    }

    public static ResponseEntity<SignInResponseDto> success(String token) {
        SignInResponseDto responseBody = new SignInResponseDto(token);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<UserResponseValidationDto> signInFail() {
        UserResponseValidationDto responseBody = new UserResponseValidationDto(ResponseCode.SIGN_IN_FAIL, ResponseMessage.SIGN_IN_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }
}
