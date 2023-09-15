package com.microservices.userservice.repositiry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.userservice.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

}
