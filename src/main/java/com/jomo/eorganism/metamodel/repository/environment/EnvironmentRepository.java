package com.jomo.eorganism.metamodel.repository.environment;

import com.jomo.eorganism.metamodel.entity.environment.EnvironmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvironmentRepository extends JpaRepository<EnvironmentEntity, Long> {
}
