package com.panda.JSON_EX.JSON_EX.Member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    
    // 회원가입 페이지로 이동
    @GetMapping("/signup")
    public String signup() {
        return "signup.html";
    }

    // 회원가입 처리
    @PostMapping("/signupConfirm")
    public String signupConfirm(Member member) {
        memberService.signup(member);
        return "redirect:/";
    }
    // 로그인 페이지로 이동
    @GetMapping("/login")
    public String login() { return "login.html"; }


    // Spring Security를 사용하면 로그인 처리를 따로 구현할 필요가 없음
}
