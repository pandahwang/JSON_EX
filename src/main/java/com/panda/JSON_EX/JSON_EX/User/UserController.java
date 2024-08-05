package com.panda.JSON_EX.JSON_EX.User;

import lombok.RequiredArgsConstructor;
import org.hibernate.query.QueryParameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public String update() {
        userService.update();
        return "redirect:/";
    }

    @GetMapping("/sum")
    public String sum(Model model) {
        int sum = userService.sum();
        model.addAttribute("sum", sum);
        return "/getsum";
    }

    @GetMapping("/sumByID")
    public String sumByID(Model model, Long id) {
        String name = userService.getUserName(id);
        model.addAttribute("name", name + "의");
        // postcount를 sum에 저장
        int sum = userService.sumByID(id);
        model.addAttribute("sum", sum);
        return "/getsum";
    }

}
