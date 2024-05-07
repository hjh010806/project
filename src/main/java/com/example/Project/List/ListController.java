package com.example.Project.List;

import com.example.Project.Answer.AnswerForm;
import com.example.Project.SocialLogin.PrincipalDetail;
import com.example.Project.User.SiteUser;
import com.example.Project.User.UserService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class ListController {
    private final ListService listService;
    private final UserService userService;
    private final ListRepository listRepository;

    @GetMapping("/list")
    public String list(Model model) {
        List<ListMain> mainList = this.listRepository.findAllOrderByCreateDateDesc();
        model.addAttribute("listMain", mainList);

        return "main_form";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/createList")
    public String listCreate(Model model) {
        model.addAttribute("listForm", new ListForm()); // ListForm 객체를 모델에 추가
        return "list/list_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/createList")
    public String listCreate(@Valid ListForm listForm, BindingResult bindingResult, Principal principal, Model model) {
        if (bindingResult.hasErrors())
            return "list/list_form";

        if (listForm.getContent().isEmpty()) {
            bindingResult.rejectValue("content", "NotEmpty", "내용을 입력하세요.");
            model.addAttribute("errorMessage", "내용을 입력하세요."); // 오류 메시지 추가
            return "list/list_form";
        }

        SiteUser siteUser = userService.getUser(principal.getName());
        this.listService.create(listForm.getContent(), siteUser);
        return "redirect:/";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm){
        ListMain listMain =this.listService.getListMain(id);
        model.addAttribute("listMain", listMain);

        return "list/list_detail";
    }
}
