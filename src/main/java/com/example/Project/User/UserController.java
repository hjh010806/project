package com.example.Project.User;

import com.example.Project.Likes.LikesService;
import com.example.Project.List.ListMain;
import com.example.Project.List.ListService;
import com.example.Project.SocialLogin.PrincipalDetail;
import com.example.Project.SocialLogin.PrincipalDetailsService;
import com.example.Project.WebSocket.Alarm.Alarm;
import com.example.Project.WebSocket.Alarm.AlarmDto;
import com.example.Project.WebSocket.Alarm.AlarmService;
import com.example.Project.WebSocket.Chat.ChatRoom;
import com.example.Project.WebSocket.Chat.ChatRoomService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final ListService listService;
    private final ChatRoomService chatRoomService;
    private final AlarmService alarmService;

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
            userService.createUser(userCreateForm.getName(), userCreateForm.getEmail(), userCreateForm.getPassword1(),
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
    public String profile(Model model, @AuthenticationPrincipal PrincipalDetail principalDetail, @RequestParam(name = "action", required = false) String action) {
        // userDetails 객체를 통해 현재 사용자 정보에 접근 가능
        if (principalDetail != null) {
            SiteUser siteUser = userService.getUser(principalDetail.getUsername());
            List<Alarm> alarmList = siteUser != null ? alarmService.findByAcceptUser(siteUser) : Collections.emptyList();
            model.addAttribute("alarmList", alarmList);
            model.addAttribute("siteUser", siteUser);
            model.addAttribute("temp_profile", siteUser.getProfileUrl());
            if ("myList".equals(action))
                model.addAttribute("listMain", listService.getAuthorList(siteUser.getId()));
            else if ("likes".equals(action))
                model.addAttribute("listMain", listService.getLikesList(siteUser.getId()));
            else
                model.addAttribute("listMain", listService.getAuthorList(siteUser.getId()));
        }
        return "profile/profile_form";
    }


    @GetMapping("/{id}/profile")
    public String userProfile(Model model, @PathVariable("id") Long id, Principal principal, @RequestParam(name = "action", required = false) String action) {
        // userDetails 객체를 통해 현재 사용자 정보에 접근 가능
        SiteUser profileUser = this.userService.getUserId(id);
        if (principal != null) {
            SiteUser user = userService.getUser(principal.getName());
            model.addAttribute("siteUser", user);
            List<Alarm> alarmList = user != null ? alarmService.findByAcceptUser(user) : Collections.emptyList();
            model.addAttribute("alarmList", alarmList);
        }
        if (profileUser != null) {
            model.addAttribute("profileUser", profileUser);
            if ("myList".equals(action))
                model.addAttribute("listMain", listService.getAuthorList(id));
            else if ("likes".equals(action))
                model.addAttribute("listMain", listService.getLikesList(id));
            else
                model.addAttribute("listMain", listService.getAuthorList(id));
        }
        return "profile/profile_user_form";
    }

    @PostMapping("/update")
    @PreAuthorize("isAuthenticated()")
    public String updateUserProfile(@AuthenticationPrincipal PrincipalDetail principalDetail,
                                    @RequestParam("name") String name,
                                    @RequestParam("email") String email,
                                    @RequestParam("nickName") String nickName,
                                    @RequestParam("number") String number,
                                    RedirectAttributes redirectAttributes) {
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


    @RequestMapping("/findPw")
    public String find_pw_form() throws Exception {
        return "login/passwordChange";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/updatePw")
    public String updatePw(@AuthenticationPrincipal PrincipalDetail principalDetail,
                           @Valid UserPasswordForm userPasswordForm,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/user/profile"; // 오류가 있으면 프로필 페이지로 다시 이동
        }

        // 새로운 비밀번호 확인
        if (!userPasswordForm.getNewPassword1().equals(userPasswordForm.getNewPassword2())) {
            bindingResult.rejectValue("newPassword2", "passwordInCorrect", "새로운 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            return "profile/profile_form";
        }
        // 사용자의 아이디를 가져옵니다.
        Long userId = principalDetail.getId();

        // 비밀번호 변경을 userService에 위임합니다.
        userService.updateUserPassword(userId, userPasswordForm.getPassword(), userPasswordForm.getNewPassword1());
        return "redirect:/user/profile";
    }


    @GetMapping("/search")
    public String search(Model model, @RequestParam("kw") String kw, Principal principal) {
        List<SiteUser> searchResults = userService.searchByKeyword(kw);
        model.addAttribute("userProfile", searchResults);
        if (principal != null) {
            SiteUser user = userService.getUser(principal.getName());
            model.addAttribute("siteUser", user);
        }
        return "list/search_list";
    }

    @GetMapping("/talk/{id}")
    public String talk(Model model, @PathVariable("id") Long friendId, @AuthenticationPrincipal UserDetails
            userDetails) {
        SiteUser ownerUser = userService.getUser(userDetails.getUsername());
        SiteUser friendUser = userService.getUserId(friendId);
        Optional<ChatRoom> chatRoom = chatRoomService.getChatRoom(ownerUser, friendUser);
        if (chatRoom.isPresent()) {
            model.addAttribute("chatRoom", chatRoom.get());
        } else {
            ChatRoom chatRoom1 = ChatRoom.builder().user1(ownerUser).user2(friendUser).build();
            chatRoom1 = chatRoomService.save(chatRoom1);
            model.addAttribute("chatRoom", chatRoom1);
        }
        model.addAttribute("ownerUser", ownerUser);
        model.addAttribute("friendUser", friendUser);
        return "chat/chatRoom";
    }

    @GetMapping("/alarmCheck/{chatRoomId}")
    public String alarmCheck(@PathVariable("chatRoomId") Long chatRoomId, @AuthenticationPrincipal UserDetails userDetails) {
        SiteUser siteUser = userService.getUser(userDetails.getUsername());
        ChatRoom chatRoom = chatRoomService.findById(chatRoomId);
        List<Alarm> alarmList = alarmService.findByChatRoomId(chatRoomId);
        for (Alarm alarm : alarmList) {
            if (alarm.getAcceptUser() == siteUser) {
                alarmService.delete(alarm);
            }
        }
        if (siteUser != chatRoom.getUser1())
            return "redirect:/user/talk/%d".formatted(chatRoom.getUser1().getId());
        else
            return "redirect:/user/talk/%d".formatted(chatRoom.getUser2().getId());
    }

    @GetMapping("/notifications")
    public List<Alarm> getNotifications(@AuthenticationPrincipal PrincipalDetail principalDetail) {
        if (principalDetail != null) {
            SiteUser user = userService.getUser(principalDetail.getUsername());
            return alarmService.findByAcceptUser(user);
        }
        return Collections.emptyList();
    }

}
