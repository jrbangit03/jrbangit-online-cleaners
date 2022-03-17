package com.jrbangit.onlinecleaners;

import com.jrbangit.onlinecleaners.models.Users;
import com.jrbangit.onlinecleaners.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OnlineCleanersApplication {

	@Autowired
	UsersRepository usersRepository;

	@Bean
	CommandLineRunner runner(){
		return args -> {
			Users user = new Users();
			user.setUserFn("JR");
			user.setUserLn("BANGIT");
			user.setUserId("jrbangit03");
			user.setPassword("test");
			usersRepository.save(user);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(OnlineCleanersApplication.class, args);
	}

}
