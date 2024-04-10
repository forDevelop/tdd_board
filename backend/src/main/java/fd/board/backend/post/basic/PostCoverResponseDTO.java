package fd.board.backend.post.basic;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostCoverResponseDTO {
    private Long postId;
    private String title;
    private String nickname;
    private LocalDateTime time;
    private Integer like;
    private Integer view;

    public PostCoverResponseDTO(Long postId, String title, String nickname, LocalDateTime time, Integer like, Integer view){
        this.postId = postId;
        this.title = title;
        this.nickname = nickname;
        this.time = time;
        this.like = like;
        this.view = view;
    }
}
