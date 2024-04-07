package fd.board.backend.user;

import fd.board.backend.ApiTest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest
public class UserControllerTest extends ApiTest {

    @Test
    public void 회원가입() throws Exception {
        //given
        SignUpRequestDTO request = UserSteps.회원가입요청_생성();

        //when
        ExtractableResponse<Response> response = UserSteps.회원가입요청(request);

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());

    }

}
