package swe.gw.apiproject.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import swe.gw.apiproject.model.Users;
import swe.gw.apiproject.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public Users createUsers(Users input)
    {
        if (input.getUsername() == null || input.getRole() == null || input.getPassword() == null) {
            return null;
        }
        if(this.getUsersByUsername(input.getUsername()).isPresent()) {
            return null;
        }
        input.setPassword(passwordEncoder.encode(input.getPassword()));
        return usersRepository.save(input);
    }

    public List<Users> getUsers() { return usersRepository.findAll();}

    public Users updateUsers(Long id, Users input) {
        Optional<Users> rawData = this.getUsersById(id);

        if (rawData.isEmpty()) {
            return null;
        }
        Users data = (Users) rawData.get();
        // check that a new username is provided and that it does not already exist
        if(!Objects.isNull(input.getUsername()) || this.getUsersByUsername(input.getUsername()).isEmpty())  {
            data.setUsername(input.getUsername());
        }
        if(!Objects.isNull(input.getPassword())) {data.setPassword(input.getPassword());}
        if(!Objects.isNull(input.getEmail())) {data.setEmail(input.getEmail());}
        return usersRepository.save(data);
    }

    public Optional<Users> getUsersById(Long id) { return usersRepository.findById(id);}

    public Optional<Users> getUsersByUsername(String username) { return usersRepository.findByUsername(username);}

    public Optional<Users> getUsersByEmail(String email) { return usersRepository.findByEmail(email);}

    public Users deleteUsersById(Long id) {
        Optional<Users> data = this.getUsersById(id);
        if (data.isEmpty()) {
            return null;
        }
        usersRepository.deleteById(id);
        return data.get();
    }
    public ResponseEntity<String> noUserError() {return ResponseEntity.badRequest().body("user not found");}
    public Map userResponse(Users user) {
        Map <String, String> userRes = new HashMap<String, String>();
        userRes.put("role", user.getRole());
        userRes.put("email", user.getEmail());
        userRes.put("username", user.getUsername());
        userRes.put("id", user.getId().toString());
        return userRes;
    }
}
