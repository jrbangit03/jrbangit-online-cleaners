package com.jrbangit.onlinecleaners.repositories;

import com.jrbangit.onlinecleaners.models.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Long> {

    Users findUserDetailsByUserId(String userID);
}
