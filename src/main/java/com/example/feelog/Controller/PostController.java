package com.example.feelog.Controller;

import com.example.feelog.DTO.PostRequest;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import  org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import com.example.feelog.Entity.Member;
import com.example.feelog.Entity.Post;
import com.example.feelog.Service.PostService;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@Slf4j
public class PostController {
    private final PostService postService; // blogService -> postService
    @RequestMapping("/postwriteAction/{blogId}")
    public String createPost(Model model){
        model.addAttribute("post", new Post());
        return "write.html";
    }
    @PostMapping("/postwriteAction/{blogId}") //blogwrite -> postwriteAction
    public String create(@ModelAttribute("post") PostRequest post,
                         @SessionAttribute(name = "login", required = false) Member member,
                         @PathVariable Long blogId)

      {  if(member == null){
            log.info("member session is not valid");
            return "index.html";

        }

        postService.create(post,member,blogId);
        return "index.html";

    }
}