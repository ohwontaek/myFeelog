package com.example.feelog.Controller;

import com.example.feelog.DTO.BlogRequest;
import com.example.feelog.DTO.CommentRequest;
import com.example.feelog.Entity.Blog;
import com.example.feelog.Entity.Member;
import com.example.feelog.Entity.Post;
import com.example.feelog.Repository.LikeRepository;
import com.example.feelog.Service.BlogService;
import com.example.feelog.Service.CommentService;
import com.example.feelog.Service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Pageable;

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
        ModelAndView mv = new ModelAndView("redirect:/index");
        return mv;
    }

    @RequestMapping("/blogpost/{postId}")
    public ModelAndView blogPost(@PathVariable Long postId) {


        ModelAndView mv = new ModelAndView("blog-post.html");
        Optional<Post> postOptional = postService.findByPostId(postId);
        if(postOptional.isPresent()) {
            Post post = postOptional.get();
            mv.addObject("post", post);
            mv.addObject("writer", postService.getWriterById(postId));
            mv.addObject("likes", postService.getLikeByPostId(postId));
            mv.addObject("comments", commentService.getCommentsByPostId(postId));
//            mv.addObject("like", postService.getLikeByPostId(postId));
        }else{
            mv.setStatus(HttpStatusCode.valueOf(404));
        }
        return mv;

    }

    @PostMapping("/blogpost/commentAction")
    public ModelAndView commentAction(CommentRequest commentRequest, HttpSession session){
        System.out.println(commentRequest);
        commentService.insertComment(commentRequest);

        ModelAndView mv = new ModelAndView("redirect:/blogpost/" + commentRequest.getPost_id());

        return mv;

    }

    @GetMapping("/blogpost/likeAction/{post}/{member}")
    public ModelAndView likeAction(@PathVariable("member") Long memberId,
                                    @PathVariable("post") Long postId){
        postService.addLike(postId,memberId);
        ModelAndView mv = new ModelAndView("redirect:/blogpost/" + postId);
        return mv;
    }


    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/bloglist")
    public ModelAndView blogList(@PageableDefault Pageable pageable) {
        Page<Blog> blogList = blogService.getBlogList(pageable);
        ModelAndView mv = new ModelAndView("blog-list.html");
        mv.addObject("blogList", blogList);

        return mv;
    }

}
