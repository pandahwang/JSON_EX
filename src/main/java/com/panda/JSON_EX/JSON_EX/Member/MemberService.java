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
        if(memberRepository.existsByUsername(member.getUsername())) {
            throw new IllegalArgumentException("이미 존재하는 ID입니다.");
        }
        else if(memberRepository.existsByEmail(member.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 Email입니다.");
        }
        member.setPassword(passwordEncoder.encode(member.getPassword()));   // 비밀번호 암호화
        memberRepository.save(member);
    }

}
