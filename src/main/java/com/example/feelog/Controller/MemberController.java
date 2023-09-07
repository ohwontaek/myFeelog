package com.example.feelog.Controller;

import com.example.feelog.DTO.LoginRequest;
import com.example.feelog.DTO.RegisterRequest;
import com.example.feelog.Entity.Member;
import com.example.feelog.Service.RegisterService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
            mv.setViewName("signInFail.html");
        }else{
            session.setAttribute("login",member.get());
            mv.setViewName("redirect:/index");
        }
        System.out.println("login member = " + member);
        return mv;
    }

    @GetMapping("/logoutAction") // 로그아웃
    public ModelAndView logoutAction(HttpSession session){
        ModelAndView mv = new ModelAndView();

        // 세션 무효화
        session.invalidate();

        // 로그아웃 후 리다이렉트할 페이지 설정
        mv.setViewName("redirect:/index");

        return mv;
    }

    @RequestMapping ("/memberUpdate/{memberId}")
    public ModelAndView update(@PathVariable Long memberId) {
        Optional<Member> optionalMember = registerService.getById(memberId);

        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            ModelAndView mv = new ModelAndView("updateForm.html");
            mv.addObject("member", member);
            return mv;
        } else {
            // 해당 회원을 찾을 수 없는 경우에 대한 처리
            // 에러 메시지를 포함한 ModelAndView를 반환합니다.
            ModelAndView mv = new ModelAndView("updateForm");
            mv.addObject("member", new Member()); // 빈 Member 객체를 생성하여 폼을 초기화
            mv.addObject("errorMessage", "해당 회원을 찾을 수 없습니다."); // 에러 메시지를 추가
            return mv;
        }
    }

    @PostMapping("/memberUpdateAction/{memberId}")
    public ModelAndView updateAction(@PathVariable Long memberId,
                                     @RequestParam String newName,
                                     @RequestParam String newEmail,
                                     @RequestParam String newPassword,
                                     @RequestParam String newIntroduce,
                                     HttpSession session) {
        session.setAttribute("login",registerService.updateMember(memberId, newName, newEmail, newPassword, newIntroduce));
        return new ModelAndView("redirect:/index");
    }

    @GetMapping("/deleteMember/{memberId}")
    public ModelAndView deleteMember(@PathVariable Long memberId, HttpSession session) {
        Optional<Member> optionalMember = registerService.getById(memberId);
        System.out.println("memberId"+memberId);
        if (optionalMember.isPresent()) {
            // 해당 회원을 찾은 경우에 삭제 처리를 진행합니다.
            registerService.deleteMember(memberId);

            // 세션에서 로그인 정보를 제거합니다.
            session.setAttribute("login",null);
            session.invalidate();
            ModelAndView mv = new ModelAndView("redirect:/index");

            // 회원 삭제 후 어떤 페이지로 리다이렉트할지 지정합니다.
            return mv; // 홈 페이지로 리다이렉트 또는 다른 페이지로 설정 가능
        } else {
            // 해당 회원을 찾을 수 없는 경우에 대한 처리
            // 에러 메시지를 포함한 ModelAndView를 반환합니다.
            ModelAndView mv = new ModelAndView("updateForm");
            mv.addObject("member", new Member()); // 빈 Member 객체를 생성하여 폼을 초기화
            mv.addObject("errorMessage", "해당 회원을 찾을 수 없습니다."); // 에러 메시지를 추가
            return mv;
        }
    }


}
