package com.eskinfotechweb.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.eskinfotechweb.domain.Role;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
	Optional<Role> findByName(String name);
}
