package com.jomo.eorganism.metamodel.service.component;

import com.jomo.eorganism.metamodel.dto.component.ComponentCountDto;
import com.jomo.eorganism.metamodel.entity.component.ComponentEntity;
import com.jomo.eorganism.metamodel.repository.component.ComponentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

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
