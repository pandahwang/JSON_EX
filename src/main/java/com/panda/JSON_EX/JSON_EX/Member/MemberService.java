package com.panda.JSON_EX.JSON_EX.Member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입 처리
    public void signup(Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));   // 비밀번호 암호화
        memberRepository.save(member);
    }

    // 아이디 중복 확인
    public boolean isUsernameTaken(String username) {
        return memberRepository.findByUsername(username).isPresent();
    }

    // 이메일 중복 확인
    public boolean isEmailTaken(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }

}
