package com.example.Project.List;

import com.example.Project.Answer.AnswerForm;
import com.example.Project.User.SiteUser;
import com.example.Project.User.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public String listCreate(Model model, ListForm listForm) {
        model.addAttribute("destination", "createList");
        return "list/list_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/createList")
    public String listCreate(@Valid ListForm listForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors())
            return "list/list_form";

        SiteUser siteUser = userService.getUser(principal.getName());
        this.listService.create(listForm.getContent(), siteUser);
        return "redirect:/";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, Principal principal, AnswerForm answerForm) {
        ListMain listMain = this.listService.getListMain(id);
        if (!listMain.getAuthor().getEmail().equals(principal.getName())) {
            model.addAttribute("listMain", listMain);
            return "list/detail_view_other";
        }
        model.addAttribute("listMain", listMain);
        return "list/list_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String listModify(Model model, ListForm listForm, @PathVariable("id") Integer id, Principal principal) {
        if (principal == null) {
            return "main_form";
        }
        ListMain listMain = this.listService.getListMain(id);
        if (!listMain.getAuthor().getEmail().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        model.addAttribute("destination", "modify/" + id.toString());
        listForm.setContent(listMain.getContent());
        return "list/list_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String modifyList(@Valid ListForm listForm, BindingResult bindingResult, Principal principal, @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors())
            return "list/list_form";

        ListMain listMain = this.listService.getListMain(id);

        if (!listMain.getAuthor().getEmail().equals(principal.getName()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");

        this.listService.modify(listMain, listForm.getContent());
        return String.format("redirect:/home/detail/%s", id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String deleteList(Principal principal, @PathVariable("id") Integer id) {
        ListMain listMain = this.listService.getListMain(id);
        if (!listMain.getAuthor().getEmail().equals(principal.getName()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        this.listService.delete(listMain);
        return "redirect:/";
    }
}
