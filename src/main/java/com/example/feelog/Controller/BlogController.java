package com.example.feelog.Controller;

import com.example.feelog.Entity.Blog;
import com.example.feelog.Service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;

    @RequestMapping("/createblog")
    public ModelAndView createBlog(){
        ModelAndView mv = new ModelAndView("create-blog.html");
        return mv;
    }

    @PostMapping("/createblogAction")
    public ModelAndView createBlogAction(){
        Optional<Blog> blog = blogService.insertBlog();
        ModelAndView mv = new ModelAndView("create-blog.html");
        if(blog.isEmpty()){ // 로그인 실패시
            mv.setViewName("contact.html");
        }else{
            mv.setViewName("index.html");
        }

        return mv;
    }

}
