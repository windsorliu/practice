package com.windsor.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//使用Controller而不是RestController
@Controller
public class ThymeleafController {

    //返回類型一定要是String
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/index")
    public String index(Model model) {
        Student student = new Student();
        student.setId(1);
        student.setName("Judy");

        model.addAttribute("myStudent", student);

        return "index";
    }

    @PostMapping("/login")
    public String login(String userName,
                        String password,
                        Model model) {

        model.addAttribute("userName",userName);
        model.addAttribute("password",password);

        return "login";
    }
}
