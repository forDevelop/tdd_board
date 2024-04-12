package fd.board.backend.post.basic;

import fd.board.backend.ApiTest;
import fd.board.backend.user.UserSteps;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PostControllerTest extends ApiTest {

    @Test
    public void 게시글_작성() throws Exception {
        //given
        Long userId = UserSteps.회원가입요청(UserSteps.회원가입요청_생성()).jsonPath().getLong("id");

        var request = PostSteps.게시글작성요청_생성(userId);

        //when
        var response = PostSteps.게시글작성요청(request);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());

    }

    @Test
    public void 게시글_목록조회() throws Exception {
        //given
        Long userId = UserSteps.회원가입요청(UserSteps.회원가입요청_생성()).jsonPath().getLong("id");
        PostSteps.게시글작성요청(PostSteps.게시글작성요청_생성(userId));

        var request = PostSteps.게시글목록조회요청_생성();

        //when
        var response = PostSteps.게시글목록조회요청(request);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void 게시글_목록조회_예외처리() throws Exception {
        //given
        var request = PostSteps.게시글_목록조회_예외처리_요청생성();

        //when
        var response = PostSteps.게시글목록조회요청(request);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }


    @Test
    public void 게시글세부조회() throws Exception {
        //given
        long userId = UserSteps.회원가입요청(UserSteps.회원가입요청_생성()).jsonPath().getLong("id");
        PostSteps.게시글작성요청(PostSteps.게시글작성요청_생성(userId));
        List<PostCoverResponseDTO> responseDTOS = PostSteps.게시글목록조회요청(PostSteps.게시글목록조회요청_생성()).jsonPath().getList("content", PostCoverResponseDTO.class);

        var request = responseDTOS.get(0).getPostId();

        //when
        var response = PostSteps.게시글세부조회요청(request);

        //then
        assertThat(response.jsonPath().getString("title")).isEqualTo("제목");
    }


    @Test
    public void 게시글수정() throws Exception {
        //given
        Long userId = UserSteps.회원가입요청(UserSteps.회원가입요청_생성()).jsonPath().getLong("id");
        PostSteps.게시글작성요청(PostSteps.게시글작성요청_생성(userId));
        List<PostCoverResponseDTO> responseDTOS = PostSteps.게시글목록조회요청(PostSteps.게시글목록조회요청_생성()).jsonPath().getList("content", PostCoverResponseDTO.class);
        Long postId = responseDTOS.get(0).getPostId();

        var request = PostSteps.게시글수정요청_생성(userId);


        //when
        var response = PostSteps.게시글수정요청(request, postId);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value();
//        assertThat(response.statusCode()).isEqualTo(HttpStatus.ACCEPTED.value());

    }


    @Test
    public void 게시글삭제() throws Exception {
        //given
        Long userId = UserSteps.회원가입요청(UserSteps.회원가입요청_생성()).jsonPath().getLong("id");
        PostSteps.게시글작성요청(PostSteps.게시글작성요청_생성(userId));
        List<PostCoverResponseDTO> responseDTOS = PostSteps.게시글목록조회요청(PostSteps.게시글목록조회요청_생성()).jsonPath().getList("content", PostCoverResponseDTO.class);
        Long postId = responseDTOS.get(0).getPostId();

        //when
        var response = PostSteps.게시글삭제요청(userId, postId);


        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.statusCode()).isEqualTo(HttpStatus.ACCEPTED.value());
//        assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

}
