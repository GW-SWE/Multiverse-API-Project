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

    public Optional<Users> getUsersById(Long id) { return usersRepository.findById(id);}
}
