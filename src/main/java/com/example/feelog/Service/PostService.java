package com.example.feelog.Service;

import com.example.feelog.DTO.PostRequest;
import com.example.feelog.Entity.Blog;
import com.example.feelog.Entity.Post;
import com.example.feelog.Repository.BlogRepository;
import com.example.feelog.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.feelog.Entity.Member;

import com.example.feelog.Repository.PostRepository;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PostService {
    @Autowired
    private final PostRepository postRepository;
    @Autowired
    private final BlogRepository blogRepository;

    @Autowired
    private final MemberRepository memberRepository;

   /* public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    */

    public Post create(PostRequest postDTO, Member member, Long blogId){
        Blog blog = blogRepository.getReferenceById(blogId);
        Post post = new Post(postDTO,member,blog);
        post.setMember(member);
        return postRepository.save(post);
    }
   /*public void createNewBlogPost(String title, String discription, String imageUrl) {
        // 여기서 필요한 데이터를 모델에 설정하고 저장하는 비즈니스 로직을 구현합니다.
        // 예를 들어, Board 엔티티를 생성하고 저장할 수 있습니다.

        // 예제 코드:
        Board board = new Board();
        board.setTitle(board.getTitle());
        board.setDescription(board.getDescription());
        board.setImageUrl(board.getImageUrl());

        // 이미지 파일 업로드 및 저장 등의 로직을 추가할 수 있습니다.
        // imageFile을 서버에 업로드하고 파일 경로를 저장하는 등의 작업을 수행합니다.

        // 레포지토리를 사용하여 데이터베이스에 저장합니다.
        boardRepository.save(board);
    }

  */

    @Transactional
    public List<Post> findALL(){
        return postRepository.findAll();
    }
}
