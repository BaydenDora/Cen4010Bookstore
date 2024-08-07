package app.bookstore.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.bookstore.domain.User;
import app.bookstore.dto.UserDTO;
import app.bookstore.exception.User.UserExistsException;
import app.bookstore.exception.User.UserNotFoundException;
import app.bookstore.repo.UserRepo;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userDTOs = userRepo.findAll().stream()
                                .map(UserDTO::new)
                                .collect(Collectors.toList());
        return ResponseEntity.ok(userDTOs);
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(new UserDTO(verifyUser(id)));
    }*/

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String username) {
        return ResponseEntity.ok(new UserDTO(verifyUser(username)));
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User savedUser = userRepo.save(verifyUser(userDTO));
        userDTO.setUserID(savedUser.getUserID());
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }

    @PatchMapping("/{username}/edit/username/{newname}")
    public ResponseEntity<List<UserDTO>> updateUsername(@PathVariable String username, @PathVariable String newname) {
        // List<User> users = userRepo.findByUsername(username);
        List<User> users = userRepo.findByUsernameList(username);
        if (!users.isEmpty()) {
            List<UserDTO> userDTOs = users.stream().map(user -> {
                user.setUsername(newname);
                userRepo.save(user);
                return new UserDTO(user);
            }).collect(Collectors.toList());
            return ResponseEntity.ok(userDTOs);
        }
        else{
            return ResponseEntity.noContent().build();
        }
    }

    @PatchMapping("/{username}/edit/name/{newname}")
    public ResponseEntity<List<UserDTO>> updateName(@PathVariable String username, @PathVariable String newname) {
        List<User> users = userRepo.findByUsernameList(username);
        if (!users.isEmpty()) {
            List<UserDTO> userDTOs = users.stream().map(user -> {
                user.setName(newname);
                userRepo.save(user);
                return new UserDTO(user);
            }).collect(Collectors.toList());
            return ResponseEntity.ok(userDTOs);
        }
        else{
            return ResponseEntity.noContent().build();
        }
    }

    @PatchMapping("/{username}/edit/password/{newpass}")
    public ResponseEntity<List<UserDTO>> updatePassword(@PathVariable String username, @PathVariable String newpass) {
        List<User> users = userRepo.findByUsernameList(username);
        if (!users.isEmpty()) {
            List<UserDTO> userDTOs = users.stream().map(user -> {
                user.setPassword(newpass);
                userRepo.save(user);
                return new UserDTO(user);
            }).collect(Collectors.toList());
            return ResponseEntity.ok(userDTOs);
        }
        else{
            return ResponseEntity.noContent().build();
        }
    }

    @PatchMapping("/{username}/edit/email/{newemail}")
    public ResponseEntity<List<UserDTO>> updateEmail(@PathVariable String username, @PathVariable String newemail) {
        List<User> users = userRepo.findByUsernameList(username);
        if (!users.isEmpty()) {
            List<UserDTO> userDTOs = users.stream().map(user -> {
                user.setEmailAddress(newemail);
                userRepo.save(user);
                return new UserDTO(user);
            }).collect(Collectors.toList());
            return ResponseEntity.ok(userDTOs);
        }
        else{
            return ResponseEntity.noContent().build();
        }
    }

    @PatchMapping("/{username}/edit/address/{newadd}")
    public ResponseEntity<List<UserDTO>> updateAddress(@PathVariable String username, @PathVariable String newadd) {
        List<User> users = userRepo.findByUsernameList(username);
        if (!users.isEmpty()) {
            List<UserDTO> userDTOs = users.stream().map(user -> {
                user.setHomeAddress(newadd);
                userRepo.save(user);
                return new UserDTO(user);
            }).collect(Collectors.toList());
            return ResponseEntity.ok(userDTOs);
        }
        else{
            return ResponseEntity.noContent().build();
        }
    }


    private User verifyUser(String username) throws UserNotFoundException {
        return userRepo.findByMyUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
    }

    private User verifyUser(UserDTO userDTO) throws UserExistsException {
        Optional.of(userDTO.getUsername())
                .ifPresent(username -> { userRepo.findByMyUsername(username)
                .ifPresent(User -> { throw new UserExistsException(username); });});
        
        String username = userDTO.getUsername();
        userRepo.findByMyUsername(userDTO.getUsername())
                .ifPresent(User ->  { throw new UserExistsException(username); });

        User user = new User();
        user.setUsername(username);
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setEmailAddress(userDTO.getEmailAddress());
        user.setHomeAddress(userDTO.getHomeAddress());
        return user;
    }

}