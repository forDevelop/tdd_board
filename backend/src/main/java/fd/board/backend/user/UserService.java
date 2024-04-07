package fd.board.backend.user;

public interface UserService {

    public User findById(Long userId);

    public User save(User user);
}
