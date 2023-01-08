package com.jomo.eorganism.metamodel.repository.application;

import com.jomo.eorganism.metamodel.entity.application.ApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Long> {
}
