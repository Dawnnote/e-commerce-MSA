package com.shipshoe.userservice.service;


import com.shipshoe.userservice.dto.auth.*;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto);
    ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto);
    ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto);
    ResponseEntity<? super SignUpResponseDto> signUp (@Valid SignUpRequestDto dto);

    ResponseEntity<? super SignInResponseDto> logIn (@Valid SignInRequestDto dto, HttpServletResponse response);
}
