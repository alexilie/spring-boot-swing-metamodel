package com.jomo.eorganism.metamodel.service.component;


import com.jomo.eorganism.metamodel.dto.component.ComponentCountDto;
import com.jomo.eorganism.metamodel.entity.component.ComponentEntity;
import com.jomo.eorganism.metamodel.util.annotation.TransactionalReadOnly;
import com.jomo.eorganism.metamodel.util.annotation.TransactionalWrite;

import java.util.List;

public interface ComponentService {

    @TransactionalReadOnly
    List<ComponentEntity> findAll();

    @TransactionalWrite
    void save(ComponentEntity client);

    @TransactionalWrite
    void remove(ComponentEntity client);

}
