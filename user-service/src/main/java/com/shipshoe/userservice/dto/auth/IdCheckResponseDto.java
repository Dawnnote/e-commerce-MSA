package com.shipshoe.userservice.dto.auth;

import com.shipshoe.userservice.common.ResponseCode;
import com.shipshoe.userservice.common.ResponseMessage;
import com.shipshoe.userservice.dto.UserResponseValidationDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class IdCheckResponseDto extends UserResponseValidationDto {

    public IdCheckResponseDto() {
        super();
    }

    public static ResponseEntity<IdCheckResponseDto> success() {
        IdCheckResponseDto responseBody = new IdCheckResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<UserResponseValidationDto> duplicatedId() {
        UserResponseValidationDto responseBody = new UserResponseValidationDto(ResponseCode.DUPLICATE_EMAIL, ResponseMessage.DUPLICATE_EMAIL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

}
