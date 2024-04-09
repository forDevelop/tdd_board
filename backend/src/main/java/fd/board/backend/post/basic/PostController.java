package fd.board.backend.post.basic;

import fd.board.backend.user.User;
import fd.board.backend.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserService userService;


    @PostMapping
    public ResponseEntity<?> writePost(@RequestBody WritePostRequestDTO request) {

        User user = userService.findById(request.userId());
        Post post = new Post(user, request.title(), request.content(), request.imageUrl());
        Post writenPost = postService.save(post);

        if (writenPost != null) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping
    public ResponseEntity<?> findPostList(Pageable pageable) {

        Page<PostCover> response;
        try {
            response = postService.findList(pageable);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }

        return ResponseEntity.ok(response);
    }
}
