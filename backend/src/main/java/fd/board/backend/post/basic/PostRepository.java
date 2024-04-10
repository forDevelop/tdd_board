package fd.board.backend.post.basic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select new fd.board.backend.post.basic.PostCoverResponseDTO(p.id, p.title, p.user.nickname, p.createdAt, p.likeCount, p.viewCount) from Post p")
    Page<PostCoverResponseDTO> findPostCoverPage(Pageable pageable);
}
