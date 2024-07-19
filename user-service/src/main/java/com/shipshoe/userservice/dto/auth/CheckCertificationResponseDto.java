package com.shipshoe.userservice.dto.auth;

import com.shipshoe.userservice.common.ResponseCode;
import com.shipshoe.userservice.common.ResponseMessage;
import com.shipshoe.userservice.dto.UserResponseValidationDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class CheckCertificationResponseDto extends UserResponseValidationDto {

    public CheckCertificationResponseDto() {
        super();
    }

    public static ResponseEntity<CheckCertificationResponseDto> success() {
        CheckCertificationResponseDto responseBody = new CheckCertificationResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<UserResponseValidationDto> certificationFail () {
        UserResponseValidationDto responseBody = new UserResponseValidationDto(ResponseCode.CERTIFICATION_FAIL, ResponseMessage.CERTIFICATION_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }

}
