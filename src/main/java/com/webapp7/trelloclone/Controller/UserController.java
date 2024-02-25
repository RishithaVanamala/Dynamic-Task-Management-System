package com.webapp7.trelloclone.Controller;

import com.webapp7.trelloclone.Model.User;
import com.webapp7.trelloclone.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.webapp7.trelloclone.dto.UserPostAPIResponse;

@Controller
//The path for all our APIs begins with "/trello." (after Application path)
@RequestMapping(path="/trello")
public class UserController {

    @Autowired
    private UserService userService;

    // This API is used to create a new user in the system
    @PostMapping(path="/addUser")
    public @ResponseBody UserPostAPIResponse addNewUser(@RequestParam Long suid, @RequestParam String name, @RequestParam String email) {
        User user = new User();
        user.setId(suid);
        user.setName(name);
        user.setEmail(email);

        userService.save(user);

        // Create a success response
        UserPostAPIResponse response = new UserPostAPIResponse("success", "User saved successfully.", suid);

        return response;
    }

    // This API is used to retrieve and display information about all the users
    @GetMapping(path="/allUsers")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON data
        return userService.getAllUsers();
    }
}
