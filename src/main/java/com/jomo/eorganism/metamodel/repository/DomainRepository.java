package com.jomo.eorganism.metamodel.repository;

import com.jomo.eorganism.metamodel.entity.DomainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomainRepository extends JpaRepository<DomainEntity, Long> {
}
