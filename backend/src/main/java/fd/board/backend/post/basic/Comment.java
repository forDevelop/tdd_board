package fd.board.backend.post.basic;

import fd.board.backend.global.BaseEntity;
import fd.board.backend.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

@Entity
@Table(name = "comments")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(length = 200)
    private String content;

    public Comment(User user, Post post, String content) {
        Assert.notNull(id, "id는 필수입니다.");
        Assert.notNull(user, "userId는 필수입니다.");
        Assert.notNull(post, "postId는 필수입니다.");
        Assert.hasText(content, "content는 필수입니다.");
        this.user = user;
        this.post = post;
        this.content = content;
    }
}
