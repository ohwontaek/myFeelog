package com.example.feelog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.example.feelog.Service.PostService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import com.example.feelog.Entity.Post;



@Controller


public class PopularPostController {

    private final PostService postService;

    @Autowired
    public PopularPostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/popularPost")
    public String popularPost(Model model) {
        // 좋아요가 가장 많은 상위 3개의 포스트를 가져오는 메서드를 PostService에서 호출하세요.
        List<Post> popularPosts = postService.getPopularPosts(3);

        // 가져온 포스트를 모델에 추가하여 뷰로 전달합니다.
        model.addAttribute("popularPosts", popularPosts);

        // popularPost.html 또는 다른 뷰 이름으로 설정하여 해당 뷰를 반환합니다.
        return "popularPosts";
    }
}
