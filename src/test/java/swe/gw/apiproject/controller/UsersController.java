package swe.gw.apiproject.controller;

import swe.gw.apiproject.model.Users;
import swe.gw.apiproject.service.UsersService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersService usersService;

    @GetMapping("/finduser/{id}")
    public Optional<Users> readUsers(@PathVariable(value = "id") Long id) {return usersService.getUsersById(id);}
}
