package com.nexgenvision;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nexgenvision.io.entity.UserEntity;


@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

	UserEntity findByEmail(String email);
	UserEntity findByUserId(String userId);
}
