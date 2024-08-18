package com.panda.JSON_EX.JSON_EX.Member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {


    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 사용자 이름으로 회원 정보 조회
        Optional<Member> result = memberRepository.findByUsername(username);

        // Optional에 값이 없으면 UsernameNotFoundException 발생
        if(result.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        // Optional에서 값을 꺼내기 위해 get() 메소드 사용
        Member member = result.get();

        // 권한 설정 (이렇게 써야함)
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("일반유저"));

        // 아래 User 타입은 UserDetailsService의 User 타입임.
        return new User(member.getUsername(), member.getPassword(), roles);
    }
}
