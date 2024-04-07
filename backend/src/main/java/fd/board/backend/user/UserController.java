package fd.board.backend.user;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequestDTO request) {

        User user = new User(request.nickname(), request.email(), request.profileImageUrl());
        User savedUser = userService.signUp(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping("/exist/{userId}")
    public ResponseEntity<?> existUser(@PathVariable Long userId) {


        Boolean isExist = userService.exist(userId);

        if (!isExist) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();

    }
}
