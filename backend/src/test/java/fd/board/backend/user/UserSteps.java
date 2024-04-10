package fd.board.backend.user;


import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class UserSteps {


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

    static Long 사용자조회요청_생성() {
        회원가입요청(회원가입요청_생성());
        return 1L;
    }

    static ExtractableResponse<Response> 사용자조회요청(Long userId) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/v1/users/{userId}", userId)
                .then().log().all().extract();
    }
}
