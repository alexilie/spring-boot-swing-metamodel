package com.jomo.eorganism.metamodel.repository;

import com.jomo.eorganism.metamodel.entity.ReleaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReleaseRepository extends JpaRepository<ReleaseEntity, Long> {
}
