package com.eskinfotechweb.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import com.eskinfotechweb.domain.User;
import com.eskinfotechweb.repositories.UserRepository;

@Configuration
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		User joao = new User("João", "Souza", "joao@gmail.com");
		User maria = new User("Maria", "Teixeira", "maria@gmail.com");
		User jose = new User("José", "Silva", "jose@gmail.com");
		User ana = new User("Ana", "Rosa", "ana@gmail.com");
		
		createUserIfNotFound(joao);
		createUserIfNotFound(maria);
		createUserIfNotFound(jose);
		createUserIfNotFound(ana);
	}

	private User createUserIfNotFound(final User user) {
		Optional<User> obj = userRepository.findByEmail(user.getEmail());
		if (obj.isPresent()) {
			return obj.get();
		}
		return userRepository.save(user);
	}
}
