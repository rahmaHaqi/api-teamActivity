package com.mat.api.repository;

import com.mat.api.models.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<ProjectEntity,Long> {

}
