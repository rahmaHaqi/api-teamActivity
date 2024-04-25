package com.mat.api.repository;

import com.mat.api.models.profiles.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Boolean existsByUsername(String username);

    Optional<UserEntity> findByUsername(String username);
}
