package com.eskinfotechweb.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eskinfotechweb.domain.User;

@RestController
@RequestMapping("/users")
public class UserResource {

	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> users = new ArrayList<User>();
		User joao = new User("João", "Souza", "joao@gmail.com");
		User maria = new User("Maria", "Teixeira", "maria@gmail.com");
		users.addAll(Arrays.asList(joao, maria));
		return ResponseEntity.ok().body(users);
	}
	
}
