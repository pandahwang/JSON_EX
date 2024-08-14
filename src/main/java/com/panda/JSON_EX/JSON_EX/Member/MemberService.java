package com.panda.JSON_EX.JSON_EX.Member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public void register(Member member) {
        if(isMemberExist(member.getUsername())) {
            throw new IllegalArgumentException("이미 존재하는 ID입니다.");
        }
        member.setPassword(encoder.encode(member.getPassword()));
        memberRepository.save(member);
    }

    public boolean isMemberExist(String username) {
        return memberRepository.existsById(username);
    }
}
