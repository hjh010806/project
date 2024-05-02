package com.example.Project.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class ListController {
    private final ListRepository listRepository;

    @GetMapping("/list")
    public String list(Model model) {
        List<ListMain> mainList = this.listRepository.findAll();
        model.addAttribute("mainList", mainList);
        return "main_form";
    }
}
