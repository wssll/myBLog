package com.sll.blog.web;

import com.sll.blog.po.User;
import com.sll.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String registerPage(){
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password1,
                           @RequestParam String password2,
                           @RequestParam String email,
                           @RequestParam String nickname,
                           RedirectAttributes attributes){
        if(userService.checkUser(username)!=null){
            attributes.addFlashAttribute("mg","用户名已存在");
            return "redirect:/register";
        }
        User user = userService.save(username, password1, password2, email, nickname);
        if (user == null){
            attributes.addFlashAttribute("mg","两次输入的密码不一致");
            return "redirect:/register";
        }else {
            attributes.addFlashAttribute("mg","注册成功");
            return "redirect:/user";
        }

    }

}
