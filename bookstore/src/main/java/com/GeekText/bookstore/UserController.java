package com.GeekText.bookstore;
import com.GeekText.repo.UserRepository;
import java.util.NoSuchElementException;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;





@RestController 
@RequestMapping(path="/bookstore") // URL's start with /demo (after Application path)
public class UserController {
    @Autowired // Gets the bean called userRepository
    private UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostMapping(path="/add") // POST request
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody String addNewUser (@RequestParam String username, 
        @RequestParam String password, @RequestParam String emailAddress) {
        // @ResponseBody - the returned String is the response
        // @RequestParam - a parameter from the GET or POST request

        User n = new User(username, password, emailAddress);
        userRepository.save(n);
        return "Saved\n";
    }

    @GetMapping(path="/all") // GET request
    public @ResponseBody List<User> getAllUsers() {
        // Returns a JSON or XML with the users
        return this.userRepository.findAll();
    }

    // @PutMapping(path="/all")
    // public User updateUser(@PathVariable("id")int id, @RequestBody User user) {
    //     //TODO: process PUT request
    //     if (id != user.getUserID()){
    //         throw new BadRequestException("Invalid id\n");
    //     }
    //     return this.userRepository.save(user);
    // }
    
    // @DeleteMapping(path="/all")
    // public void deleteUser(){

    // }

    // private User verifyUser(int userID) throws NoSuchElementException{
    //     return userRepository.findById(userID).orElseThrow(() ->
    //         new NoSuchElementException("Error: user does not exist.\n"));
    // }

    // @ResponseStatus(HttpStatus.NOT_FOUND)
    // @ExceptionHandler(NoSuchElementException.class)
    // public String return400(NoSuchElementException e){
    //     return e.getMessage();
    // }

}