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
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable());
            // 어떤 페이지에 로그인 검사를 할지 설정
        http.authorizeHttpRequests((authorize) ->
            // 로그인 여부 상관 없이 항상 허용하는 구문
            // authorize.requestMatchers("/**").permitAll()

            // 로그인 여부에 따라 허용하는 구문
            authorize.requestMatchers("/sum").authenticated()
                    .requestMatchers("/mypage").authenticated()
                    .anyRequest().permitAll()
        );

        // 로그인 form, 페이지 지정 구문
        http.formLogin((formLogin) -> formLogin.loginPage("/login") // 로그인 페이지
                .defaultSuccessUrl("/")                             // 로그인 성공 페이지
        );

        // 로그아웃 설정
        http.logout((logout) -> logout.logoutUrl("/logout") // 로그아웃 요청 경로
                .logoutSuccessUrl("/")                       // 로그아웃 성공 후 이동 경로
        );

        // 예외 처리 설정
        http.exceptionHandling((exceptions) ->
                exceptions.authenticationEntryPoint((request, response, authException) ->
                        response.sendRedirect("/accessDenied"))
        );
        return http.build();
    }
}