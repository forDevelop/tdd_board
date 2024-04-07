package fd.board.backend.user;

import org.springframework.util.Assert;

public record SignUpRequestDTO(String nickname, String email, String profileImageUrl) {
    public SignUpRequestDTO {
        Assert.hasText(nickname, "닉네임은 필수입니다.");
        Assert.hasText(email, "이메일은 필수입니다.");
//            Assert.hasText(profileImageUrl, "프로필 이미지 URL은 필수입니다.");
    }
}
