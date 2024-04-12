package fd.board.backend.post.basic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.nio.file.AccessDeniedException;

public interface PostService {
    Post save(Post post);

    Page<PostCoverResponseDTO> findList(Pageable request);

    PostDetailResponseDTO findDetail(Long postId);

    void modify(Long postId, ModifyPostRequestDTO request) throws AccessDeniedException;

    void delete(Long postId, Long userId) throws AccessDeniedException;
}
