package fd.board.backend.user;


import fd.board.backend.post.basic.Role;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class UserSteps {
    public static User 사용자등록_생성() {
        Role role = Role.USER;
        return new User(role);
    }


    public static SignUpRequestDTO 회원가입요청_생성() {
        String nickname = "닉네임";
        String email = "이메일";
        String profileImageUrl = "프로필 이미지";
        SignUpRequestDTO request = new SignUpRequestDTO(nickname, email, profileImageUrl);
        return request;
    }

    public static ExtractableResponse<Response> 회원가입요청(SignUpRequestDTO request) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/api/v1/users/signup")
                .then().log().all().extract();
    }
}
