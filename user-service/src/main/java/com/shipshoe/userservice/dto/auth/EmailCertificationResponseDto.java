package com.shipshoe.userservice.dto.auth;

import com.shipshoe.userservice.common.ResponseCode;
import com.shipshoe.userservice.common.ResponseMessage;
import com.shipshoe.userservice.dto.UserResponseValidationDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class EmailCertificationResponseDto extends UserResponseValidationDto {

    private EmailCertificationResponseDto() {
        super();
    }

    public static ResponseEntity<EmailCertificationResponseDto> success() {
        EmailCertificationResponseDto responseBody = new EmailCertificationResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<UserResponseValidationDto> duplicateId() {
        UserResponseValidationDto responseBody = new UserResponseValidationDto(ResponseCode.DUPLICATE_EMAIL, ResponseMessage.DUPLICATE_EMAIL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

}
