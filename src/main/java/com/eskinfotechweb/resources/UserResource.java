package com.eskinfotechweb.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eskinfotechweb.domain.Role;
import com.eskinfotechweb.domain.User;
import com.eskinfotechweb.dto.UserDTO;
import com.eskinfotechweb.services.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> users = userService.findAll();
		List<UserDTO> usersDTO = users.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
		return ResponseEntity.ok().body(usersDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User user = userService.findById(id);
		return ResponseEntity.ok().body(new UserDTO(user));
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {		
		User user = userService.fromDTO(userDTO);		
		return ResponseEntity.ok().body(new UserDTO(userService.create(user)));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable String id,  @RequestBody UserDTO userDTO) {
		User user = userService.fromDTO(userDTO);
		user.setId(id);
		return ResponseEntity.ok().body(new UserDTO(userService.update(user)));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}/roles")
	public ResponseEntity<List<Role>> findRoles(@PathVariable String id) {
		User user = userService.findById(id);		
		return ResponseEntity.ok().body(user.getRoles());
	}
}
