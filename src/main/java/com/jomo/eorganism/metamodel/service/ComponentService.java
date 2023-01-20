package com.jomo.eorganism.metamodel.service;

import com.jomo.eorganism.metamodel.entity.ComponentEntity;
import com.jomo.eorganism.metamodel.util.TransactionalReadOnly;
import com.jomo.eorganism.metamodel.util.TransactionalWrite;

import java.util.List;

public interface ComponentService {

    @TransactionalReadOnly
    List<ComponentEntity> findAll();

    @TransactionalWrite
    void save(ComponentEntity client);

    @TransactionalWrite
    void remove(ComponentEntity client);

}
