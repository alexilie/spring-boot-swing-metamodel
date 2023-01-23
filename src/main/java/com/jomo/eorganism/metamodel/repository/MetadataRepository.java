package com.jomo.eorganism.metamodel.repository;

import com.jomo.eorganism.metamodel.entity.MetadataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetadataRepository extends JpaRepository<MetadataEntity, Long> {
}
