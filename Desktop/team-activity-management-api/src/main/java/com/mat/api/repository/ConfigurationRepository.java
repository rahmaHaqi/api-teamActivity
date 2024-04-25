package com.mat.api.repository;

import com.mat.api.models.ConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigurationRepository extends JpaRepository<ConfigurationEntity, Long> {
}
