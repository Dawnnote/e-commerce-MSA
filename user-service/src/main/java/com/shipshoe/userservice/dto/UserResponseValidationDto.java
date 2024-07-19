package com.shipshoe.userservice.dto;

import com.shipshoe.userservice.common.ResponseCode;
import com.shipshoe.userservice.common.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
public class UserResponseValidationDto {

    private String code;
    private String message;

    public UserResponseValidationDto() {
        this.code = ResponseCode.SUCCESS;
        this.message = ResponseMessage.SUCCESS;
    }

    public static ResponseEntity<UserResponseValidationDto> databaseError() {
        UserResponseValidationDto responseBody = new UserResponseValidationDto(ResponseCode.DATABASE_ERROR, ResponseMessage.DATABASE_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }

    public static ResponseEntity<UserResponseValidationDto> validationFail() {
        UserResponseValidationDto responseBody = new UserResponseValidationDto(ResponseCode.VALIDATION_FAIL, ResponseMessage.VALIDATION_FAIL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

}
