package com.spring.example.usermanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.spring.example.usermanagement.entity.Role;
import com.spring.example.usermanagement.entity.User;
import com.spring.example.usermanagement.repository.UserRepository;

@SpringBootApplication
public class UsermanagementApplication implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(UsermanagementApplication.class, args);
	}
		
	
	@Override
	public void run(String... args) {
		// TODO Auto-generated method stub
		
		User adminAccount = userRepository.findByRole(Role.ADMIN);
		if(null == adminAccount) {
			User user = new User();
			
			user.setEmail("admin@gmail.com");
			user.setFirstname("admin");
			user.setSecondname("admin");
            user.setRole(Role.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
		}
	}
}


