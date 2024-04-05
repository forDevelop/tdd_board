package fd.board.backend.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;


    @Test
    public void 사용자_등록() throws Exception {
        //given
        User user = UserSteps.사용자등록_생성();

        //when
        User createdUser = userService.save(user);

        //then
        assertThat(createdUser.getId()).isEqualTo(user.getId());
    }

    @Test
    public void 사용자_조회() throws Exception {
        //given
        User user = UserSteps.사용자등록_생성();
        User createdUser = userService.save(user);
        Long userId = createdUser.getId();


        //when
        User findUser = userService.findById(userId);

        //then
        assertThat(findUser.getId()).isEqualTo(userId);
    }



}

