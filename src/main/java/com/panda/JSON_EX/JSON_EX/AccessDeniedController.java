package com.panda.JSON_EX.JSON_EX;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 예외 처리 클래스
@Controller
public class AccessDeniedController {
    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }
}