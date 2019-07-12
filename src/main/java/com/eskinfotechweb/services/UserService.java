package com.eskinfotechweb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eskinfotechweb.domain.User;
import com.eskinfotechweb.dto.UserDTO;
import com.eskinfotechweb.repositories.UserRepository;
import com.eskinfotechweb.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User findById(String id) {
		Optional<User> user = userRepository.findById(id); 
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User create(User user) {
		return userRepository.save(user);
	}
	
	public User fromDTO(UserDTO userDTO) {
		return new User(userDTO);
	}
}
