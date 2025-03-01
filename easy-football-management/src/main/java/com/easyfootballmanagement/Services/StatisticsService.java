package com.easyfootballmanagement.Services;

import com.easyfootballmanagement.application.common.exception.BusinessException;
import com.easyfootballmanagement.domain.entities.Statistics;
import com.easyfootballmanagement.infrastructure.repository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsService implements IService<Statistics> {

    @Autowired
    private StatisticsRepository repository;

    @Override
    public List<Statistics> get() {
        return repository.findAll();
    }

    @Override
    public Statistics getById(long id) throws BusinessException {
        var result = repository.findById(id);
        if (!result.isPresent()){
            throw new BusinessException("Entidade não esta inserida em nossa base");
        }
        return result.get();
    }

    @Override
    public void delete(long id) throws BusinessException {
        repository.delete(this.getById(id));
    }

    @Override
    public void insert(Statistics model) throws BusinessException {
        repository.save(model);
    }

    @Override
    public void update(Statistics model) throws BusinessException {
        repository.deleteById(model.getId());
        repository.save(model);
    }
}
