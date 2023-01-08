package com.jomo.eorganism.metamodel.service.application;

import com.jomo.eorganism.metamodel.entity.application.ApplicationEntity;
import com.jomo.eorganism.metamodel.util.annotation.TransactionalReadOnly;
import com.jomo.eorganism.metamodel.util.annotation.TransactionalWrite;

import java.util.List;

public interface ApplicationService {

    @TransactionalReadOnly
    List<ApplicationEntity> findAll();

    @TransactionalWrite
    void remove(ApplicationEntity address);

    @TransactionalWrite
    void save(ApplicationEntity address);
}
