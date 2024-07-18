package app.bookstore.controller;

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
        List<User> users = userRepo.findAll();
        List<UserDTO> userDTOs = users.stream().map(UserDTO::new)
                                .collect(Collectors.toList());
        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {
        User user = verifyUser(id);
        UserDTO userDTO = new UserDTO(user);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User user = verifyUser(userDTO);
        User savedUser = userRepo.save(user);
        userDTO.setUserID(savedUser.getUserID());
        return ResponseEntity.ok(userDTO);
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



    private User verifyUser(int id) throws UserNotFoundException{
        return userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }


    private User verifyUser(UserDTO userDTO) throws UserExistsException{
        Optional.of(userDTO.getUserID())
                .ifPresent(id -> { userRepo.findById(id)
                .ifPresent(User -> { throw new UserExistsException(id); });});
        
        String username = userDTO.getUsername();
        userRepo.findByUsername(userDTO.getUsername())
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