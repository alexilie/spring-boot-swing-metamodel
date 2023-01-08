package com.jomo.eorganism.metamodel.repository.component;

import com.jomo.eorganism.metamodel.entity.component.ComponentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ComponentRepository extends JpaRepository<ComponentEntity, Long> {

    @Query(value = "SELECT c.name AS name, c.type AS type, COUNT(a.id) AS count " +
            "FROM ApplicationEntity a ")
    Object[][] getComponentCount();

}
