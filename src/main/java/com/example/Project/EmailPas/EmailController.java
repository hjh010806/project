package com.example.Project.EmailPas;

import com.example.Project.User.SiteUser;
import com.example.Project.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

@Controller
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {
    private final UserService userService;
    private final EmailService emailService;

    private static final char[] rndAllCharacters = new char[]{
            //number
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            //uppercase
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            //lowercase
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            //special symbols
            '@', '$', '!', '%', '*', '?', '&'
    };

    @PostMapping("/password")
    public String password(@RequestParam(value = "email", defaultValue = "") String userEmail, @RequestParam(value = "name",defaultValue = "") String userName, Model model) {
        if(userEmail.equals("") && userName.equals("")){
            model.addAttribute("error","이메일과 아이디를 확인해주세요.");
            return "changePassword";
        }else if(userEmail.equals("")){
            model.addAttribute("error","이메일은 필수입니다.");
            return "changePassword";
        }else if(userName.equals("")){
            model.addAttribute("error","아이디는 필수입니다.");
            return "changePassword";
        }
        SiteUser user = userService.userEmailCheck(userEmail, userName);
        if (user == null)
            return "redirect:/user/findPw";
        //            return "changePassword";

        Random r = new Random();

        int max = 8 + r.nextInt(5);
        StringBuilder _password = new StringBuilder();
        for (int i = 0; i < max; i++)
            _password.append(rndAllCharacters[r.nextInt(rndAllCharacters.length)]);
        String password = _password.toString();

        emailService.sendEmail(userEmail, "비밀번호 변경 내용입니다.", "변경된 비밀번호는 " + password + " 입니다.");
        userService.changePassword(user, password);
        return "redirect:/user/login";
    }
}
