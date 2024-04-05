package fd.board.backend.post.basic;

import lombok.RequiredArgsConstructor;
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

}
