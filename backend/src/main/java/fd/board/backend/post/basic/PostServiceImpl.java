package fd.board.backend.post.basic;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


}
