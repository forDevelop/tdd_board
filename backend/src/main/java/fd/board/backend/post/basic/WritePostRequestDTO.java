package fd.board.backend.post.basic;

import org.springframework.util.Assert;

public record WritePostRequestDTO(Long userId, String title, String content, String imageUrl) {
    public WritePostRequestDTO {
        Assert.notNull(userId, "유저 ID는 필수입니다.");
        Assert.hasText(title, "제목은 필수입니다.");
        Assert.hasText(content, "내용은 필수입니다.");
//            Assert.hasText(imageUrl, "이미지 URL은 필수입니다.");
    }
}
