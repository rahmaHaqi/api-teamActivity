package com.mat.api.repository;

import com.mat.api.models.IssueLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueLogRepository extends JpaRepository<IssueLogEntity, Long> {
}


