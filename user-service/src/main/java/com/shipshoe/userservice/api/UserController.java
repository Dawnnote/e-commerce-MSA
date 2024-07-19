package com.shipshoe.userservice.api;

import com.shipshoe.userservice.dto.CustomUserDetails;
import com.shipshoe.userservice.dto.UserFormDto;
import com.shipshoe.userservice.dto.UserResponseDto;
import com.shipshoe.userservice.dto.auth.*;
import com.shipshoe.userservice.service.AuthService;
import com.shipshoe.userservice.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    private final Environment env;


    @GetMapping("/health_check")
    public String status() {

        return String.format("It's Working in User Service on PORT %s",
                env.getProperty("local.server.port"));
    }



    @PostMapping("/api/users/id-check")
    public ResponseEntity<? super IdCheckResponseDto> idCheck (
            @RequestBody @Valid IdCheckRequestDto requestBody)
    {
        return authService.idCheck(requestBody);
    }

    @PostMapping("/api/users/email-certification")
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification (
            @RequestBody @Valid EmailCertificationRequestDto requestBody)
    {
        return authService.emailCertification(requestBody);
    }

    @PostMapping("/api/users/check-certification")
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification (
            @RequestBody @Valid CheckCertificationRequestDto requestBody)
    {
        return authService.checkCertification(requestBody);
    }


    // 유저 회원가입
    @PostMapping("/api/users/signup")
    public ResponseEntity<? super SignUpResponseDto> signUp (
            @RequestBody @Valid SignUpRequestDto requestBody)
    {
        return authService.signUp(requestBody);
    }

    // 로그인
    @PostMapping("/api/users/login")
    public ResponseEntity<? super SignInResponseDto> login(
            @RequestBody @Valid SignInRequestDto requestBody,
            HttpServletResponse response
    ) {
        return authService.logIn(requestBody, response);
    }

    // 유저 프로필
    @GetMapping("/api/users/{userId}")
    public UserResponseDto getUser(@PathVariable("userId") Long id) {
        return userService.findUser(id);
    }

    // 유저 프로필
    @PutMapping("/api/users/{userId}")
    public UserResponseDto updateUser(@PathVariable("userId") Long id,
                                   @RequestBody UserFormDto form,
                                   @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Long userId = customUserDetails.getId();
        if (!id.equals(userId)) throw new RuntimeException("잘못된 사용자");

        return userService.updateUser(id, form);
    }
}
