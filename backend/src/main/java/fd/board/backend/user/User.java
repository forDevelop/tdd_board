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

    public User(Role role) {
        super();
        this.role = role;
        Assert.notNull(role, "유저 권한은 필수입니다.");
    }

    //==  로그인 구현시 User 정보 추가 필요!!  ==//

}
