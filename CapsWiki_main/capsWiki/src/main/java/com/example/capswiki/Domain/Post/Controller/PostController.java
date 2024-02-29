package com.example.capswiki.Domain.Post.Controller;

import com.example.capswiki.DTO.Post.RequestDTO.PostRequestDTO;
import com.example.capswiki.DTO.Post.ResponseDTO.PostResponseDTO;
import com.example.capswiki.Domain.Post.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/post") // 글 작성 완
    // ResponseEntity를 통해 요청에 대한 응답을 보냄
    public ResponseEntity<?> createPost(@RequestBody PostRequestDTO postRequestDTO) {
        //위 RequestBody를 통해 전달된 DTO를 통해 클라이언트의 요청을 처리함
        try {
            postService.createPost(postRequestDTO); //서비스의 createPost 기능을 사용
            return ResponseEntity.ok("Successfully posted a post"); // 성공적으로 수행할 시, 해당 메세지 반환
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage()); //실패 시 오류 메세지 반환
        }
    }

    @GetMapping("/post/{title}") // 게시글 조회 완
    public ResponseEntity<PostResponseDTO> getPost(@PathVariable String title) {
        return ResponseEntity.ok(postService.getPost(title));
    }

    @GetMapping("/home") // 게시글 메인화면 조회 완
    public ResponseEntity<?> getPostList(){
        return ResponseEntity.ok(postService.getPostList());
    }

    @GetMapping("/post/random") // 게시글 랜덤 조회 완
    public ResponseEntity<?> getRandomPost(){
        return ResponseEntity.ok(postService.getRandomPost());
    }

    @PutMapping("/post/{title}") // 게시글 수정 완
    public ResponseEntity<?> updatePost(@RequestBody PostRequestDTO postRequestDTO, @PathVariable String title) {
        try {
            postService.updatePost(postRequestDTO, title);
            return ResponseEntity.ok("Post successfully updated");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/post/{title}") // 게시글 삭제 완
    public ResponseEntity<?> deletePost(@PathVariable String title) {
        try {
            postService.deletePost(title);
            return ResponseEntity.ok("Post successfully deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/post/history/{title}") // 게시글 수정 내역 조회 완
    public ResponseEntity<List<PostResponseDTO>> getPostHistory(@PathVariable String title) {
        return ResponseEntity.ok(postService.getPostHistory(title));
    }

}
