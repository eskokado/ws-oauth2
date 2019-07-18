package com.eskinfotechweb.config;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.eskinfotechweb.domain.Role;
import com.eskinfotechweb.domain.User;
import com.eskinfotechweb.repositories.RoleRepository;
import com.eskinfotechweb.repositories.UserRepository;

@Configuration
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		userRepository.deleteAll();
		roleRepository.deleteAll();
		
		Role roleAdmin = createRoleIfNotFound("ROLE_ADMIN");
		Role roleUser = createRoleIfNotFound("ROLE_USER");
		
		User joao = new User(null, "João", "Souza", "joao@gmail.com", passwordEncoder.encode("123"), true);
		User maria = new User(null, "Maria", "Teixeira", "maria@gmail.com", passwordEncoder.encode("123"), true);
		User jose = new User(null, "José", "Silva", "jose@gmail.com", passwordEncoder.encode("123"), true);
		User ana = new User(null, "Ana", "Rosa", "ana@gmail.com", passwordEncoder.encode("123"), true);
		
		joao.setRoles(Arrays.asList(roleAdmin));
		maria.setRoles(Arrays.asList(roleUser));
		jose.setRoles(Arrays.asList(roleUser));
		ana.setRoles(Arrays.asList(roleUser));
		
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
	
	private Role createRoleIfNotFound(String name) {
		Optional<Role> obj = roleRepository.findByName(name);
		if (obj.isPresent()) {
			return obj.get();
		}
		return roleRepository.save(new Role(null, name));
	}
}
