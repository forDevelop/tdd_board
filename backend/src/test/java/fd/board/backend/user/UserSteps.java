package fd.board.backend.user;

import fd.board.backend.post.basic.Role;

public class UserSteps {
    public static User 사용자등록_생성() {
        Role role = Role.USER;
        return new User(role);
    }

}
