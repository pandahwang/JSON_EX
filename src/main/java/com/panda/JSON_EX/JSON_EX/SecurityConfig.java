package com.panda.JSON_EX.JSON_EX;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 비밀번호 암호화 Bean 등록
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    // 어떤 페이지에 로그인 검사를 할지 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable());
        http.authorizeHttpRequests((authorize) ->
                // 로그인 여부 상관 없이 항상 허용하는 구문
                // authorize.requestMatchers("/**").permitAll()

                // 로그인 여부에 따라 허용하는 구문
                authorize.requestMatchers("/sum").authenticated()
                        .anyRequest().permitAll()
        );
        http.formLogin((formLogin) -> formLogin.loginPage("/login") // 로그인 페이지
                .defaultSuccessUrl("/")                             // 로그인 성공 페이지

        );
        return http.build();
    }

}