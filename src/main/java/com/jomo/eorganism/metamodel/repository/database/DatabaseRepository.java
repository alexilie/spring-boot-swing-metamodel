package com.jomo.eorganism.metamodel.repository.database;

import com.jomo.eorganism.metamodel.entity.database.DatabaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseRepository extends JpaRepository<DatabaseEntity, Long> {
}
