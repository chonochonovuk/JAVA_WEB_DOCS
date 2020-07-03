package com.exam.springexam.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String index(HttpSession httpSession, Model model){
        String index = httpSession.getAttribute("user") == null ? "index" : "home";
        return index;
    }
}
