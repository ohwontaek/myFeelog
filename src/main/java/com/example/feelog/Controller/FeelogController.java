package com.example.feelog.Controller;

import ch.qos.logback.core.model.Model;
import com.example.feelog.DTO.RegisterRequest;
import com.example.feelog.Entity.Member;
import com.example.feelog.Service.RegiserService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class FeelogController {

    private RegiserService regiserService;

    public FeelogController(RegiserService regiserService) {
        this.regiserService = regiserService;
    }

    @RequestMapping({"/","/index"})
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index.html");
        return mv;
    }

    @RequestMapping("/contact")
    public ModelAndView contactForm(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("contact.html");
        return mv;
    }

    @RequestMapping("/bloghome")
    public ModelAndView blogHome(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("blog-home.html");
        return mv;
    }
    @RequestMapping("/blogpost")
    public ModelAndView blogPost(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("blog-post.html");
        return mv;
    }

    @RequestMapping("/signup")
    public ModelAndView signUp(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("signup.html");
        return mv;
    }



}