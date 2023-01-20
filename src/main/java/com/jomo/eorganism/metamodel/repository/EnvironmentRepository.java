package com.jomo.eorganism.metamodel.repository;

import com.jomo.eorganism.metamodel.entity.EnvironmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvironmentRepository extends JpaRepository<EnvironmentEntity, Long> {
}
