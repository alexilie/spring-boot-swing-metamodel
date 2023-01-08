package com.jomo.eorganism.metamodel.service.application;

import com.jomo.eorganism.metamodel.entity.application.ApplicationEntity;
import com.jomo.eorganism.metamodel.repository.application.ApplicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    public List<ApplicationEntity> findAll() {
        return applicationRepository.findAll();
    }

    public void remove(ApplicationEntity address) {
        applicationRepository.delete(address);
    }

    public void save(ApplicationEntity address) {
        applicationRepository.save(address);
    }
}
