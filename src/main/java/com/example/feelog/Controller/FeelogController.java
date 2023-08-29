package com.example.feelog.Controller;

import com.example.feelog.DTO.RegisterRequest;
import com.example.feelog.Service.RegisterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class FeelogController {

    private RegisterService registerService;

    public FeelogController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @RequestMapping({"/","/index"})
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("index.html");
        return mv;
    }

    @RequestMapping("/contact")
    public ModelAndView contactForm(){
        ModelAndView mv = new ModelAndView("contact.html");
        return mv;
    }

    @RequestMapping("/bloghome")
    public ModelAndView blogHome(){
        ModelAndView mv = new ModelAndView("blog-home.html");
        return mv;
    }
    @RequestMapping("/blogpost")
    public ModelAndView blogPost(){
        ModelAndView mv = new ModelAndView("blog-post.html");
        return mv;
    }

/*    @RequestMapping("/signup")
    public ModelAndView signUp(){
        ModelAndView mv = new ModelAndView("sign-up.html");
        return mv;
    }

    @PostMapping("/signupAction") // 회원정보 입력 시
    public ModelAndView singupAction(RegisterRequest dto){
        System.out.println("dto.getEmail() == " + dto.getEmail());
        registerService.signup(dto);
        ModelAndView mv = new ModelAndView("contact.html");
        return mv;
    }*/
    @RequestMapping("/blogwrite")
    public ModelAndView blogWrite(){
        ModelAndView mv = new ModelAndView("write.html");
        return mv;
    }
    @RequestMapping("/blogwriteAction")
    public ModelAndView blogWriteAction(/*WriteRequest request*/){
        //글 입력 처리 부분
        ModelAndView mv = new ModelAndView("blog-home.html");
        return mv;
    }

}