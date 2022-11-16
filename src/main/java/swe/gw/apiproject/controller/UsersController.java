package swe.gw.apiproject.controller;

import org.apache.logging.log4j.util.Chars;
import swe.gw.apiproject.model.Users;
import swe.gw.apiproject.service.UsersService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.print.DocFlavor;
import java.util.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersService usersService;

    @PostMapping("/create")
    // requires hashing before post
    public Users createUsers(@RequestBody Users data) { return usersService.createUsers(data);}

    @GetMapping("/all")
    public List<Users> readUsers() { return usersService.getUsers();}

    @PutMapping("/update/{id}")
    public Users readUsers(@PathVariable(value = "id") Long id, @RequestBody Users input)
    {return usersService.updateUsers(id, input);}

    @GetMapping("/finduser/{id}")
    public Optional<Users> readUsers(@PathVariable(value = "id") Long id) {return usersService.getUsersById(id);}

    @GetMapping("/findusername/{username}")
    public Optional<Users> readUsers(@PathVariable(value = "username") String username) {return usersService.getUsersByUsername(username);}

    @GetMapping("/findemail/{email}")
    public Optional<Users> readUsersForEmail(@PathVariable(value = "email") String email) {return usersService.getUsersByEmail(email);}
}
