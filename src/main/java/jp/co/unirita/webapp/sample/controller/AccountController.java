package jp.co.unirita.webapp.sample.controller;

import jp.co.unirita.webapp.sample.form.SignupForm;
import jp.co.unirita.webapp.sample.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }


    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(Model model, SignupForm form) {
        if (accountService.registerUser(form.getUserName(), form.getPassword1(), form.getPassword2())) {
            model.addAttribute("hasError", false);
            return "/login";
        }
        model.addAttribute("hasError", true);
        model.addAttribute("error", "登録に失敗しました");
        return "/signup";
    }
}
