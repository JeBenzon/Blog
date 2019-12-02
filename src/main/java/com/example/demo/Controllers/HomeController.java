package com.example.demo.Controllers;

import com.example.demo.Models.Blogopslag;
import com.example.demo.Models.User;
import com.example.demo.Services.BlogopslagService;
import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;


@Controller
public class HomeController {
    @Autowired
    UserService userService;

    @Autowired
    BlogopslagService blogopslagService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/")
    public String login(@ModelAttribute User user) {
        User user1 = userService.checkUser(user);
        System.out.println(user1);
        if (user1.getUsertype() == null) {
            return "redirect:/";
        } else if(user1.getUsertype().equals("admin")) {
            return "redirect:/admin";
        } else if(user1.getUsertype().equals("user")) {
            return "redirect:/userPage";
        }

        return "redirect:/";
    }

    @GetMapping("/userPage")
    public String userpage(Model model) {

        ArrayList<Blogopslag> blogopslag = blogopslagService.blogopslagList();
        System.out.println(blogopslag.size());
        model.addAttribute("blogopslag", blogopslag);
        return "userPage";
    }




}
