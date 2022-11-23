package swe.gw.apiproject.service;


import swe.gw.apiproject.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import swe.gw.apiproject.repository.UsersRepository;
import swe.gw.apiproject.service.MyUserDetail;
import swe.gw.apiproject.service.MyUserDetailService;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UsersRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<Users> user = userRepository.findByUsername(userName);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

        return user.map(MyUserDetail::new).get();
    }
}