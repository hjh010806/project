package com.example.Project.List;

import com.example.Project.Answer.AnswerForm;
import com.example.Project.Global.ListDTO;
import com.example.Project.Likes.LikesService;
import com.example.Project.SocialLogin.PrincipalDetail;
import com.example.Project.User.SiteUser;
import com.example.Project.User.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.*;

import static com.example.Project.User.QSiteUser.siteUser;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class ListController {
    private final ListService listService;
    private final UserService userService;
    private final LikesService likesService;

    @GetMapping("/list")
    public String list(Model model, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        List<ListMain> mainList = this.listService.getList();
        SiteUser user = principalDetail != null ? userService.getUser(principalDetail.getUsername()) : null;
        if (principalDetail != null) {
            SiteUser siteUser = userService.getUser(principalDetail.getEmail());
            model.addAttribute("siteUser", siteUser);
        }
        List<ListDTO> listDTOS = new ArrayList<>();
        for (ListMain listMain : mainList)
            listDTOS.add(ListDTO.builder().listMain(listMain).heart(user != null ? likesService.isLikes(listMain.getId(), user.getId()) : false).listUrl(listMain.getListUrl()).build());
        model.addAttribute("listDTOS", listDTOS);
        return "main_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/list/createLikes/{id}")
    public String createLikes(@PathVariable("id") int listMainId, @AuthenticationPrincipal PrincipalDetail principal) {
        SiteUser siteUser = userService.getUser(principal.getUsername());
        likesService.createLikes(listMainId, siteUser);
        return "redirect:/home/list#list_%d".formatted(listMainId);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/list/deleteLikes/{id}")
    public String deleteLikes(@PathVariable("id") long listMainId, Principal principal) {
        SiteUser siteUser = userService.getUser(principal.getName());
        likesService.deleteLikes(listMainId, siteUser.getId());
        return "redirect:/home/list#list_%d".formatted(listMainId);
    }

    @GetMapping("/checkLikes/{listMainId}/{siteUserId}")
    public ResponseEntity<?> checkLikes(@PathVariable("listMainId") long listMainId, @PathVariable("siteUserId") long siteUserId) {
        boolean isLikes = likesService.isLikes(listMainId, siteUserId);
        return ResponseEntity.ok(Collections.singletonMap("isLikes", isLikes));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/createList")
    public String listCreate(Model model, ListForm listForm, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("destination", "createList");
        // 기능을 구현하다 수정진행중 데이터유지가 필요없을거같아 지웠지만 혹시몰라 주석처리함.
//        SiteUser user = userService.getUser(userDetails.getUsername());
//        String temp = user.getImageUrl();
//        model.addAttribute("temp", temp);
        if (model.containsAttribute("text"))
            listForm.setContent((String) model.getAttribute("text"));
        model.addAttribute("siteUser", userService.getUser(userDetails.getUsername()));
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
    public String detail(Model model, @PathVariable("id") Integer id, PrincipalDetail principalDetail, Principal principal, AnswerForm answerForm) {
        ListMain listMain = this.listService.getListMain(id);
        model.addAttribute("listMain", listMain);
        if(principal != null) {
            SiteUser user = userService.getUser(principal.getName());
            model.addAttribute("siteUser", user);
        }

        return "list/list_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String listModify(Model model, ListForm listForm, @PathVariable("id") Integer id, Principal principal) {
        if (principal == null) {
            return "main_form";
        }
        ListMain listMain = this.listService.getListMain(id);
        if (!listMain.getAuthor().getEmail().equals(principal.getName()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        // 리스트
        SiteUser user = userService.getUser(listMain.getAuthor().getEmail());
        model.addAttribute("siteUser", user);

        if (user.getImageUrl() != null) {
            String temp = user.getImageUrl();
            model.addAttribute("temp", temp);
        } else {
            String temp = listMain.getListUrl();
            model.addAttribute("temp", temp);
        }
        model.addAttribute("destination", "modify/" + id.toString());
        listForm.setContent(listMain.getContent());
        return "list/list_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String modifyList(@Valid ListForm listForm, Model model, BindingResult bindingResult,
                             Principal principal, @PathVariable("id") Integer id) {
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        this.listService.delete(listMain);
        return "redirect:/";
    }

    @GetMapping("/list/{id}")
    public String userList(Model model, @PathVariable("id") Long id) {
        model.addAttribute("authorList", listService.getAuthorList(id));
        return "profile/profile_form";
    }

    @GetMapping("/myList")
    public String getMyList(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String userEmail = userDetails.getUsername();
        SiteUser siteUser = userService.getUser(userEmail);
        model.addAttribute("myList", listService.getAuthorList(siteUser.getId()));
        return "profile/profile_form";
    }
}
