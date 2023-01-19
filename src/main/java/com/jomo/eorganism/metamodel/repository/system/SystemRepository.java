package com.jomo.eorganism.metamodel.repository.system;

import com.jomo.eorganism.metamodel.entity.system.SystemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemRepository extends JpaRepository<SystemEntity, Long> {
}
