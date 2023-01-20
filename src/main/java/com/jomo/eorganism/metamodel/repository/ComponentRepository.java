package com.jomo.eorganism.metamodel.repository;

import com.jomo.eorganism.metamodel.entity.ComponentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ComponentRepository extends JpaRepository<ComponentEntity, Long> {

    @Query(value = "Select name, type, description FROM ComponentEntity")
    Object[][] getComponentCount();

}
