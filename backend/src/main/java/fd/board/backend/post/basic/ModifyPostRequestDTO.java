package fd.board.backend.post.basic;

import org.springframework.util.Assert;

public record ModifyPostRequestDTO(Long userId, String title, String content, String image) {
    public ModifyPostRequestDTO {
        Assert.notNull(userId, "userId는 null이 아니어야 합니다.");
        Assert.hasText(title, "title은 text가 있어야 합니다.");
        Assert.hasText(content, "content는 text가 있어야 합니다.");
        Assert.hasText(image, "image는 text가 있어야 합니다.");
    }

}
