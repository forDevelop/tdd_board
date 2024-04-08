package fd.board.backend.post.basic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
    public Post save(Post post);

    Page<Post> findList(Pageable pageable);
}
