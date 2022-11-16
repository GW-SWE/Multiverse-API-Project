package swe.gw.apiproject.service;

import swe.gw.apiproject.model.Users;
import swe.gw.apiproject.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    public Users createUsers(Users info) {return usersRepository.save(info);}

    public List<Users> getUsers() { return usersRepository.findAll();}

    public Users updateUsers(Long id, Users input) {
        Users data = usersRepository.findById(id).get();
        if(!Objects.isNull(input.getUsername())) {data.setUsername(input.getUsername());}
        if(!Objects.isNull(input.getPassword())) {data.setPassword(input.getPassword());}
        if(!Objects.isNull(input.getEmail())) {data.setEmail(input.getEmail());}

        return usersRepository.save(data);
    }

    public Optional<Users> getUsersById(Long id) { return usersRepository.findById(id);}

    public Optional<Users> getUsersByUsername(String username) { return usersRepository.findByUsername(username);}

    public Optional<Users> getUsersByEmail(String email) { return usersRepository.findByEmail(email);}


}
