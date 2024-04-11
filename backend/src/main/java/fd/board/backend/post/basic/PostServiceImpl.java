package fd.board.backend.post.basic;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override
    @Transactional
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Page<PostCoverResponseDTO> findList(Pageable pageable) {
        Page<PostCoverResponseDTO> postCoverPage = postRepository.findPostCoverPage(pageable);

        if (postCoverPage.isEmpty()) {
            throw new IllegalArgumentException("게시글이 존재하지 않습니다.");
        }

        return postCoverPage;
    }

    @Override
    public PostDetailResponseDTO findDetail(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new IllegalArgumentException("게시글이 존재하지 않습니다."));

        post.addViewCount();

        PostDetailResponseDTO postDetailResponseDTO = new PostDetailResponseDTO(post.getId(), post.getTitle(), post.getUser().getNickname(),
                post.getLikeCount(), post.getViewCount(), post.getCreatedAt(),
                post.getContent(), post.getComments());

        return postDetailResponseDTO;
    }

    @Override
    public void modify(Long postId, ModifyPostRequestDTO request) throws AccessDeniedException {

        Post post = postRepository.findById(postId).orElseThrow(() ->
                new IllegalArgumentException("게시글이 존재하지 않습니다."));
        if(!Objects.equals(post.getUser().getId(), request.userId())){
            throw new AccessDeniedException("게시글 작성자만 수정할 수 있습니다.");
        }
        post.modify(request.title(), request.content(), request.image());
    }

    @Override
    public void delete(Long postId, Long userId) throws AccessDeniedException {

        Post post = postRepository.findById(postId).orElseThrow(() ->
                new IllegalArgumentException("게시글이 존재하지 않습니다."));
        if(!Objects.equals(post.getUser().getId(), userId)){
            throw new AccessDeniedException("게시글 작성자만 삭제할 수 있습니다.");
        }
        postRepository.deleteById(postId);
    }


}
