package com.GeekText.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller 
@RequestMapping(path="/demo") // URL's start with /demo (after Application path)
public class MainController {
    @Autowired // Gets the bean called userRepository
    private UserRepository userRepository;

    @PostMapping(path="/add") // POST request
    public @ResponseBody String addNewUser (@RequestParam String username, 
        @RequestParam String password, @RequestParam String emailAddress) {
        // @ResponseBody - the returned String is the response
        // @RequestParam - a parameter from the GET or POST request

        User n = new User();
        n.setUsername(username);
        n.setPassword(password);
        n.setEmailAddress(emailAddress);
        userRepository.save(n);
        return "Saved\n";
    }

    @GetMapping(path="/all") // GET request
    public @ResponseBody Iterable<User> getAllUsers() {
        // Returns a JSON or XML with the users
        return userRepository.findAll();
    }
}