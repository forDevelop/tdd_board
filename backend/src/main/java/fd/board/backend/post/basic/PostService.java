package fd.board.backend.post.basic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
    Post save(Post post);

    Page<PostCoverResponseDTO> findList(Pageable request);

    PostDetailResponseDTO findDetail(Long postId);
}
