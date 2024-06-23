package app.bookstore;

import app.bookstore.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserDTO> userDTOs = users.stream().map(user -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserID(user.getUserID());
            userDTO.setUsername(user.getUsername());
            userDTO.setPassword(user.getPassword());
            userDTO.setEmailAddress(user.getEmailAddress());
            userDTO.setHomeAddress(user.getHomeAddress());
            return userDTO;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {
        Optional<User> user = userRepo.findById(id);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setUserID(user.get().getUserID());
        userDTO.setUsername(user.get().getUsername());
        userDTO.setPassword(user.get().getPassword());
        userDTO.setEmailAddress(user.get().getEmailAddress());
        userDTO.setHomeAddress(user.get().getHomeAddress());

        return ResponseEntity.ok(userDTO);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmailAddress(userDTO.getEmailAddress());
        user.setHomeAddress(userDTO.getHomeAddress());

        User savedUser = userRepo.save(user);

        userDTO.setUserID(savedUser.getUserID());
        return ResponseEntity.ok(userDTO);
    }

    // Additional methods for update and delete can be added here
}