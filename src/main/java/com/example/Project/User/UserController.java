package com.example.Project.User;

import com.example.Project.SocialLogin.PrincipalDetail;
import com.example.Project.SocialLogin.PrincipalDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final PrincipalDetailsService principalDetailsService;

    @GetMapping("/signup")
    public String signup(UserCreateForm userCreateForm) {
        return "login/login_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "login/login_form";
        }
        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 패스워드가 일치하지 않습니다.");
            return "login/login_form";
        }

        try {
            userService.create(userCreateForm.getName(), userCreateForm.getEmail(), userCreateForm.getPassword1(),
                    userCreateForm.getNickName(), userCreateForm.getNumber());
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "login/login_form";
        } catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "login/login_form";
        }

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(UserCreateForm userCreateForm) {

        return "login/login_form";
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String profile(Model model, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        // userDetails 객체를 통해 현재 사용자 정보에 접근 가능
        if (principalDetail != null) {
            SiteUser siteUser = principalDetail.getUser();
            if (siteUser != null) {
                model.addAttribute("siteUser", siteUser);
//                model.addAttribute("name", siteUser.getName());
//                model.addAttribute("email", siteUser.getEmail());
//                model.addAttribute("nickName", siteUser.getNickName());
//                model.addAttribute("number", siteUser.getNumber());
            }
        }
        return "profile/profile_form";
    }

    @PostMapping("/update")
    @PreAuthorize("isAuthenticated()")
    public String updateUserProfile(@AuthenticationPrincipal PrincipalDetail principalDetail,
                                    @RequestParam("name") String name,
                                    @RequestParam("email") String email,
                                    @RequestParam("nickName") String nickName,
                                    @RequestParam("number") String number, RedirectAttributes redirectAttributes) {
        if (userService.hasUser(number, nickName, email))
            redirectAttributes.addFlashAttribute("error", "중복된 값입니다.");
        else {
            SiteUser user = userService.updateUserProfile(name, email, nickName, number);
            if (user != null)
                principalDetail.setSiteUser(user);
        }
        // 프로필 페이지로 리다이렉트
        return "redirect:/user/profile";
    }
<<<<<<< HEAD
=======

    @RequestMapping("/findPw")
    public String find_pw_form() throws Exception {
        return "login/passwordChange";
    }
>>>>>>> aa24f00274921681faced4aa1b6aeccb532ff0a9
}
