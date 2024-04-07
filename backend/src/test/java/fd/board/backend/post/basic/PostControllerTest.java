package fd.board.backend.post.basic;

import fd.board.backend.ApiTest;
import fd.board.backend.user.User;
import fd.board.backend.user.UserService;
import fd.board.backend.user.UserSteps;
import org.apache.catalina.UserDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest
public class PostControllerTest extends ApiTest {


    @Autowired
    private PostController postController;
    @Autowired
    private UserService userService;


    @Test
    public void 게시글_작성() throws Exception {
        //given
        User savedUser = userService.save(UserSteps.사용자등록_생성());
        var request = PostSteps.게시글작성요청_생성(savedUser.getId());

        //when
        var response = PostSteps.게시글작성요청(request);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());

    }

}
