package app.bookstore;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.bookstore.dto.UserDTO;

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
            userDTO.setName(user.getName());
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
        userDTO.setName(user.get().getName());
        userDTO.setPassword(user.get().getPassword());
        userDTO.setEmailAddress(user.get().getEmailAddress());
        userDTO.setHomeAddress(user.get().getHomeAddress());

        return ResponseEntity.ok(userDTO);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setEmailAddress(userDTO.getEmailAddress());
        user.setHomeAddress(userDTO.getHomeAddress());

        User savedUser = userRepo.save(user);

        userDTO.setUserID(savedUser.getUserID());
        return ResponseEntity.ok(userDTO);
    }

    @PatchMapping("/{username}/edit/username/{newname}")
    public ResponseEntity<List<UserDTO>> updateUsername(@PathVariable String username, @PathVariable String newname) {
        List<User> users = userRepo.findByUsername(username);
        if (!users.isEmpty()) {
            List<UserDTO> userDTOs = users.stream().map(user -> {
                user.setUsername(newname);
                userRepo.save(user);
                UserDTO userDTO = new UserDTO();
                userDTO.setUserID(user.getUserID());
                userDTO.setUsername(user.getUsername());
                userDTO.setName(user.getName());
                userDTO.setPassword(user.getPassword());
                userDTO.setEmailAddress(user.getEmailAddress());
                userDTO.setHomeAddress(user.getHomeAddress());
                return userDTO;
            }).collect(Collectors.toList());
            return ResponseEntity.ok(userDTOs);
        }
        else{
            return ResponseEntity.noContent().build();
        }
    }

    @PatchMapping("/{username}/edit/name/{newname}")
    public ResponseEntity<List<UserDTO>> updateName(@PathVariable String username, @PathVariable String newname) {
        List<User> users = userRepo.findByUsername(username);
        if (!users.isEmpty()) {
            List<UserDTO> userDTOs = users.stream().map(user -> {
                user.setName(newname);
                userRepo.save(user);
                UserDTO userDTO = new UserDTO();
                userDTO.setUserID(user.getUserID());
                userDTO.setUsername(user.getUsername());
                userDTO.setName(user.getName());
                userDTO.setPassword(user.getPassword());
                userDTO.setEmailAddress(user.getEmailAddress());
                userDTO.setHomeAddress(user.getHomeAddress());
                return userDTO;
            }).collect(Collectors.toList());
            return ResponseEntity.ok(userDTOs);
        }
        else{
            return ResponseEntity.noContent().build();
        }
    }

    @PatchMapping("/{username}/edit/password/{newpass}")
    public ResponseEntity<List<UserDTO>> updatePassword(@PathVariable String username, @PathVariable String newpass) {
        List<User> users = userRepo.findByUsername(username);
        if (!users.isEmpty()) {
            List<UserDTO> userDTOs = users.stream().map(user -> {
                user.setPassword(newpass);
                userRepo.save(user);
                UserDTO userDTO = new UserDTO();
                userDTO.setUserID(user.getUserID());
                userDTO.setUsername(user.getUsername());
                userDTO.setName(user.getName());
                userDTO.setPassword(user.getPassword());
                userDTO.setEmailAddress(user.getEmailAddress());
                userDTO.setHomeAddress(user.getHomeAddress());
                return userDTO;
            }).collect(Collectors.toList());
            return ResponseEntity.ok(userDTOs);
        }
        else{
            return ResponseEntity.noContent().build();
        }
    }

    @PatchMapping("/{username}/edit/email/{newemail}")
    public ResponseEntity<List<UserDTO>> updateEmail(@PathVariable String username, @PathVariable String newemail) {
        List<User> users = userRepo.findByUsername(username);
        if (!users.isEmpty()) {
            List<UserDTO> userDTOs = users.stream().map(user -> {
                user.setEmailAddress(newemail);
                userRepo.save(user);
                UserDTO userDTO = new UserDTO();
                userDTO.setUserID(user.getUserID());
                userDTO.setUsername(user.getUsername());
                userDTO.setName(user.getName());
                userDTO.setPassword(user.getPassword());
                userDTO.setEmailAddress(user.getEmailAddress());
                userDTO.setHomeAddress(user.getHomeAddress());
                return userDTO;
            }).collect(Collectors.toList());
            return ResponseEntity.ok(userDTOs);
        }
        else{
            return ResponseEntity.noContent().build();
        }
    }

    @PatchMapping("/{username}/edit/address/{newadd}")
    public ResponseEntity<List<UserDTO>> updateAddress(@PathVariable String username, @PathVariable String newadd) {
        List<User> users = userRepo.findByUsername(username);
        if (!users.isEmpty()) {
            List<UserDTO> userDTOs = users.stream().map(user -> {
                user.setHomeAddress(newadd);
                userRepo.save(user);
                UserDTO userDTO = new UserDTO();
                userDTO.setUserID(user.getUserID());
                userDTO.setUsername(user.getUsername());
                userDTO.setName(user.getName());
                userDTO.setPassword(user.getPassword());
                userDTO.setEmailAddress(user.getEmailAddress());
                userDTO.setHomeAddress(user.getHomeAddress());
                return userDTO;
            }).collect(Collectors.toList());
            return ResponseEntity.ok(userDTOs);
        }
        else{
            return ResponseEntity.noContent().build();
        }
    }


}