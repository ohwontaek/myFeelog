package com.example.feelog.Controller;

import com.example.feelog.DTO.LoginRequest;
import com.example.feelog.DTO.RegisterRequest;
import com.example.feelog.Entity.Member;
import com.example.feelog.Service.RegisterService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
public class MemberController {

    @Autowired
    private RegisterService registerService;

    @RequestMapping("/signup")
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
    }

    @PostMapping("/loginAction") // 테스트 로그인 용
    public ModelAndView loginAction(LoginRequest dto, HttpSession session){
        ModelAndView mv = new ModelAndView();
        Optional<Member> member = registerService.login(dto);
        if(member.isEmpty()){ // 로그인 실패시
            mv.setViewName("contact.html");
        }else{
            session.setAttribute("login",member.get());
            mv.setViewName("index.html");
        }
        System.out.println("login member = " + member);
        return mv;
    }

}
