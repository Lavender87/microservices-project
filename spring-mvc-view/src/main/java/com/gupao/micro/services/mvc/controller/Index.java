package com.gupao.micro.services.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class Index {

    @GetMapping({"/",""})
    public String index(Model model){
        model.addAttribute("message","hello");
        return "index";
    }

    @ModelAttribute(name="message")
    public String message(){
        return "Hello World";
    }
}
