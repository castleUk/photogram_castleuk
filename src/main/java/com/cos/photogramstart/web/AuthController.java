package com.cos.photogramstart.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor // final 필드 Dependency injection
@Controller // IoC, 파일리턴
public class AuthController {

    private final AuthService authService;

    // public AuthController(AuthService authService) {
    // this.authService = authService;
    // }

    // 로그인 페이지
    @GetMapping("/auth/signin")
    public String signinForm() {
        return "auth/signin";

    }

    // 회원가입 페이지
    @GetMapping("/auth/signup")
    public String signupForm() {
        return "auth/signup";

    }

    // 회원가입
    @PostMapping("/auth/signup")
    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomValidationException("유효성 검사 실패함", errorMap);

        } else {

            log.info(signupDto.toString());
            User user = signupDto.toEntity();
            log.info(user.toString());
            User userEntity = authService.회원가입(user);
            System.out.println(userEntity);
            return "auth/signin";
        }

    }
}
