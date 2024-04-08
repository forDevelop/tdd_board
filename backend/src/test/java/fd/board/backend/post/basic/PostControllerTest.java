package fd.board.backend.post.basic;

import fd.board.backend.ApiTest;
import fd.board.backend.user.User;
import fd.board.backend.user.UserService;
import fd.board.backend.user.UserSteps;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest
public class PostControllerTest extends ApiTest {

    @Autowired
    private UserService userService;


    @Test
    public void 게시글_작성() throws Exception {
        //given
        User user = UserSteps.사용자등록_생성();
        User createdUser = userService.signUp(user);
        Long userId = createdUser.getId();

        var request = PostSteps.게시글작성요청_생성(userId);

        //when
        var response = PostSteps.게시글작성요청(request);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());

    }

    @Test
    public void 게시글_목록조회() throws Exception {
        //given
        UserSteps.회원가입요청(UserSteps.회원가입요청_생성());
        PostSteps.게시글작성요청(PostSteps.게시글작성요청_생성(1L));
        var request = PostSteps.게시글목록조회요청_생성();

        //when
        ExtractableResponse<Response> response = PostSteps.게시글목록조회요청(request);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());

    }


}
