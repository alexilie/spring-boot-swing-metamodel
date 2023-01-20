package com.jomo.eorganism.metamodel.service;

import com.jomo.eorganism.metamodel.entity.ComponentEntity;
import com.jomo.eorganism.metamodel.repository.ComponentRepository;
import com.jomo.eorganism.metamodel.service.ComponentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
class ComponentServiceImpl implements ComponentService {

    public static final int NAME_INDEX = 0;
    public static final int TYPE_INDEX = 1;
    public static final int DESCRIPTION_INDEX = 2;

    private final ComponentRepository componentRepository;

    public List<ComponentEntity> findAll() {
        return componentRepository.findAll();
    }

    public void save(ComponentEntity client) {
        componentRepository.save(client);
    }

    public void remove(ComponentEntity client) {
        componentRepository.delete(client);
    }

}
