package com.jomo.eorganism.metamodel.service;

import java.util.Optional;
import com.jomo.eorganism.metamodel.entity.ApplicationEntity;
import com.jomo.eorganism.metamodel.repository.ApplicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    public Optional<ApplicationEntity> findById(Long id) {
        return applicationRepository.findById(id);
    }

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
