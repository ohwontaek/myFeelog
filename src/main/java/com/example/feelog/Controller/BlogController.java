package com.example.feelog.Controller;

import com.example.feelog.DTO.BlogRequest;
import com.example.feelog.Entity.Blog;
import com.example.feelog.Entity.Member;
import com.example.feelog.Entity.Post;
import com.example.feelog.Service.BlogService;
import com.example.feelog.Service.CommentService;
import com.example.feelog.Service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;

    @RequestMapping("/createblog")
    public ModelAndView createBlog(){
        ModelAndView mv = new ModelAndView("create-blog.html");
        return mv;
    }

    @RequestMapping("/blog/{blogId}")
    public ModelAndView blog(@PathVariable Long blogId){


        ModelAndView mv = new ModelAndView("blog-home.html");
        Blog blog = blogService.getBlogById(blogId);
        mv.addObject("blog", blog);
        mv.addObject("postList", postService.findPostsByBlog(blog));
        return mv;
    }

    @PostMapping("/createblogAction")
    public ModelAndView createBlogAction(BlogRequest dto, HttpSession session){
        Member loginMember = (Member) session.getAttribute("login");
        Blog blog = blogService.insertBlog(dto,loginMember);
        ModelAndView mv = new ModelAndView("/index");
        return mv;
    }

    @RequestMapping("/blogpost/{postId}")
    public ModelAndView blogPost(@PathVariable Long postId) {


        ModelAndView mv = new ModelAndView("blog-post.html");
        Optional<Post> postOptional = postService.findByPostId(postId);
        if(postOptional.isPresent()) {
            mv.addObject("post", postService.findByPostId(postId).get());
            mv.addObject("writer", postService.getWriterById(postId));
            mv.addObject("comments", commentService.getCommentsByPostId(postId));
        }else{
            mv.setStatus(HttpStatusCode.valueOf(404));
        }
        return mv;

    }

}
