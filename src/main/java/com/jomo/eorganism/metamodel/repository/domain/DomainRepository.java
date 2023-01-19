package com.jomo.eorganism.metamodel.repository.domain;

import com.jomo.eorganism.metamodel.entity.domain.DomainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomainRepository extends JpaRepository<DomainEntity, Long> {
}
