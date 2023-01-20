package com.jomo.eorganism.metamodel.service;

import java.util.Optional;
import com.jomo.eorganism.metamodel.entity.ApplicationEntity;
import com.jomo.eorganism.metamodel.util.TransactionalReadOnly;
import com.jomo.eorganism.metamodel.util.TransactionalWrite;

import java.util.List;

public interface ApplicationService {

    @TransactionalReadOnly
    Optional<ApplicationEntity> findById(Long id);

    @TransactionalReadOnly
    List<ApplicationEntity> findAll();

    @TransactionalWrite
    void remove(ApplicationEntity address);

    @TransactionalWrite
    void save(ApplicationEntity address);
}
