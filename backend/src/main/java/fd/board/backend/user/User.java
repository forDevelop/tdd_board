package fd.board.backend.user;

import fd.board.backend.global.BaseEntity;
import fd.board.backend.post.basic.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Role role;
    private String nickname;
    private String email;
    private String profileImageUrl;

    public User(Role role) {
        super();
        this.role = role;
        Assert.notNull(role, "유저 권한은 필수입니다.");
    }

    public User(String nickname, String email, String profileImageUrl) {
        this.role = Role.USER;
        this.nickname = nickname;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        Assert.notNull(role, "유저 권한은 필수입니다.");
        Assert.hasText(nickname, "닉네임은 필수입니다.");
        Assert.hasText(email, "이메일은 필수입니다.");
//        Assert.hasText(profileImageUrl, "프로필 이미지 URL은 필수입니다.");
    }

}
