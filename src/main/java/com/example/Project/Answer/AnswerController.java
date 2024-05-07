package com.example.Project.Answer;

import com.example.Project.List.ListMain;
import com.example.Project.List.ListService;
import com.example.Project.User.SiteUser;
import com.example.Project.User.UserService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    private final AnswerService answerService;
    private final ListService listService;
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id, AnswerForm answerForm, Principal principal) {
        ListMain listMain = this.listService.getListMain(id);
        model.addAttribute("listMain", listMain);
        return "list_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id, @Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal) {
        ListMain listMain = this.listService.getListMain(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());
        model.addAttribute("listMain", listMain);
        if(bindingResult.hasErrors())
            return "list_detail";
        Answer answer = answerService.create(listMain, answerForm.getContent(), siteUser);
        return String.format("redirect:/home/detail/%s", answer.getListMain().getId());
    }
}
