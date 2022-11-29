package swe.gw.apiproject.controller;

import org.apache.catalina.User;
import org.apache.logging.log4j.util.Chars;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    public ResponseEntity createUsers(@RequestBody Users data) {

        // checks if user data provided
        if (data == null) return ResponseEntity.badRequest().body("no data provided");

        // create user
        Users user = usersService.createUsers(data);

        // send user creation response
        return ResponseEntity.ok().body(usersService.userResponse(user));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Users>> readUsers() {
        List<Users> list = usersService.getUsers();
        return ResponseEntity.ok().body(list);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> readUsers(@PathVariable(value = "id") Long id, @RequestBody Users input) {

        // checks if user input data provided
        if (input == null) return ResponseEntity.badRequest().body("no data provided");

        Users newUser = usersService.updateUsers(id, input);

        // checks if user exists
        if (newUser == null) {
            return usersService.noUserError();
        }
        // send user update response
        return ResponseEntity.ok().body("user updated");
    }

    @GetMapping("/id/{id}")
    public ResponseEntity readUsers(@PathVariable(value = "id") Long id) {
        Users user = usersService.getUsersById(id).get();
        if (user == null) {
            return usersService.noUserError();
        }
        return ResponseEntity.ok().body(user);}

    @GetMapping("/username/{username}")
    public ResponseEntity readUsers(@PathVariable(value = "username") String username) {

        Users user = usersService.getUsersByUsername(username).get();
        if (user == null) {
            return usersService.noUserError();
        }
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/email/{email}")
    public Optional<Users> readUsersForEmail(@PathVariable(value = "email") String email) {return usersService.getUsersByEmail(email);}

    @DeleteMapping("/id/{id}")
    public ResponseEntity deleteUsersById(@PathVariable(value = "id") Long id) {
        Users isDeleted = usersService.deleteUsersById(id);

        if (isDeleted == null) {
            return usersService.noUserError();
        };
        return ResponseEntity.ok().body(usersService.userResponse(isDeleted));
    }
}