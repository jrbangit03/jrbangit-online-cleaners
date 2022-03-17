package com.jrbangit.onlinecleaners.services;

import com.jrbangit.onlinecleaners.models.Users;
import com.jrbangit.onlinecleaners.repositories.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
public class LoginService implements UserDetailsService {
    @Autowired
    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Users user = usersRepository.findUserDetailsByUserId(userId);
        return new User(user.getUserId(), user.getPassword(), Collections.emptyList());
    }
}
