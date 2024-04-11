package fd.board.backend.post.basic;

import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

public record PostDetailResponseDTO(Long postId, String title, String nickname, int like, int view,
                                    LocalDateTime time, String content,
                                    List<Comment> comments) {

    public PostDetailResponseDTO {
        Assert.notNull(postId, "postId는 필수입니다.");
        Assert.hasText(title, "title은 필수입니다.");
        Assert.hasText(nickname, "nickname은 필수입니다.");
        Assert.isTrue(like >= 0, "like는 0 이상이어야 합니다.");
        Assert.isTrue(view >= 0, "view는 0 이상이어야 합니다.");
        Assert.notNull(time, "time은 필수입니다.");
        Assert.hasText(content, "content는 필수입니다.");
//            Assert.notNull(comments, "comments는 필수입니다.");
    }

    public String getTitle() {
        return title;
    }
}
