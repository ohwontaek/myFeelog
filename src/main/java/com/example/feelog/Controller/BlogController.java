package com.example.feelog.Controller;

import com.example.feelog.DTO.BlogRequest;
import com.example.feelog.Entity.Blog;
import com.example.feelog.Entity.Member;
import com.example.feelog.Service.BlogService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("/blog/{blogId}")
    public ModelAndView blog(@PathVariable Long blogId){


        ModelAndView mv = new ModelAndView("blog-home.html");
        mv.addObject("blog", blogService.getBlogById(blogId));

        return mv;
    }

    @PostMapping("/createblogAction")
    public ModelAndView createBlogAction(BlogRequest dto, HttpSession session){
        Member loginMember = (Member) session.getAttribute("login");
        Blog blog = blogService.insertBlog(dto,loginMember);
        ModelAndView mv = new ModelAndView("index.html");
        return mv;
    }

}
