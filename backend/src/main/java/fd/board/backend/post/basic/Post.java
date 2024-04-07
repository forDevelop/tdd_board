package fd.board.backend.post.basic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fd.board.backend.global.BaseEntity;
import fd.board.backend.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;


@Entity
@Table(name = "posts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String imageUrl;

    private int likeCount;
    private int viewCount;
    private boolean isNotice;


    //== User 연관관계 생성 필요 ==//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;


    public Post(User user, String title, String content, String imageUrl) {
        super();
        this.user = user;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        Assert.notNull(user, "유저는 필수입니다.");
        Assert.hasText(title, "제목은 필수입니다.");
        Assert.hasText(content, "내용은 필수입니다.");
//            Assert.hasText(imageUrl, "이미지 URL은 필수입니다.");
        this.likeCount = 0;
        this.viewCount = 0;
        this.isNotice = user.getRole() == Role.ADMIN;
    }
}
