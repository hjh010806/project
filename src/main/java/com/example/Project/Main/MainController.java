package com.example.Project.Main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @GetMapping("/")
    public String main() {
//        return "main_form";
        return "redirect:/home/list";
    }


}
