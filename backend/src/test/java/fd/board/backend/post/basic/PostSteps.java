package fd.board.backend.post.basic;

import fd.board.backend.user.User;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class PostSteps {
    static WritePostRequestDTO 게시글작성요청_생성(Long userId) {

        String title = "제목";
        String content = "내용";
        String imageUrl = "이미지 URL";
        return new WritePostRequestDTO(userId, title, content, imageUrl);
    }

    static ExtractableResponse<Response> 게시글작성요청(WritePostRequestDTO request) {
        return RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post("/api/v1/posts")
                .then()
                .log().all().extract();
    }
}
