package fd.board.backend.post.basic;

import fd.board.backend.user.User;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    static Pageable 게시글목록조회요청_생성() {
        return PageRequest.of(0, 10);
    }
    static ExtractableResponse<Response> 게시글목록조회요청(Pageable request) {
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .param("pageable", request)
                .get("/api/v1/posts")
                .then().log().all().extract();

        return response;
    }

    static Pageable 게시글_목록조회_예외처리_요청생성() {
        int page = 1;
        int size = 10;
        Pageable request = PageRequest.of(page, size);
        return request;
    }
}
