package com.panda.JSON_EX.JSON_EX.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/update")
    public String update() {
        userService.update();
        return "redirect:/";
    }

    @GetMapping("/sum")
    public String sum(Model model, Long id) {
        int sum = 0;
        if(id == null) {
            sum = userService.sum();
            model.addAttribute("name", "전체");
            model.addAttribute("sum", sum);
        } else {
            String name = userService.getUserName(id);
            sum = userService.sumByID(id);
            model.addAttribute("name", name + "의");
            model.addAttribute("sum", sum);
        }
        return "/getsum";
    }

//    @GetMapping("/sumByID")
//    public String sumByID(Model model, Long id) {
//        String name = userService.getUserName(id);
//        model.addAttribute("name", name + "의");
//        System.out.println(id);
//        // postcount를 sum에 저장
//        int sum = userService.sumByID(id);
//        model.addAttribute("sum", sum);
//        return "/getsum";
//    }

}
