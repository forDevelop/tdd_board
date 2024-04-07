package fd.board.backend.user;

public interface UserService {

    public User findById(Long userId);

    public User signUp(User user);

    Boolean exist(Long userId);
}
