package com.jrbangit.onlinecleaners.services;

import com.jrbangit.onlinecleaners.models.Users;
import com.jrbangit.onlinecleaners.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findUserDetailsByUserId(username);
        return new User(user.getUserId(), user.getPassword(), Collections.emptyList());
    }
}
