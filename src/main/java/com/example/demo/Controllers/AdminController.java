package com.example.demo.Controllers;

import com.example.demo.Models.Blogopslag;
import com.example.demo.Services.BlogopslagService;
import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;


@Controller
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    BlogopslagService blogopslagService;



    @GetMapping("/admin")
    public String admin(Model model) {
        ArrayList<Blogopslag> blogopslag = blogopslagService.blogopslagList();
        System.out.println(blogopslag.size());
        model.addAttribute("blogopslag", blogopslag);
        return "Admin/Admin";
    }

    @GetMapping("/create")
    public String Create(Model model) {
        model.addAttribute("blogopslag", new Blogopslag());
        return "Admin/Create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Blogopslag blogopslag) {
        blogopslagService.CreateOpslag(blogopslag);
        return "redirect:/admin";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("blogopslag", blogopslagService.SelectOpslag(id));
        return "Admin/Edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Blogopslag blogopslag){
        blogopslagService.UpdateOpslag(blogopslag);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("blogopslag", blogopslagService.SelectOpslag(id));
        return "Admin/Delete";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute Blogopslag blogopslag) {
        blogopslagService.DeleteOpslag(blogopslag.getId());
        return "redirect:/admin";
    }

}