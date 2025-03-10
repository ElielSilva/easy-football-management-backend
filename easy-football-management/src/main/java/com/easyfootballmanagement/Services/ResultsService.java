package com.easyfootballmanagement.Services;

import com.easyfootballmanagement.application.common.exception.BusinessException;
import com.easyfootballmanagement.domain.entities.Results;
import com.easyfootballmanagement.infrastructure.repository.ResultsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultsService implements IService<Results> {

    @Autowired
    private ResultsRepository repository;

    @Override
    public List<Results> get() {
        return repository.findAll();
    }

    @Override
    public Results getById(long id) throws BusinessException {
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
    public void insert(Results model) throws BusinessException {
        repository.save(model);
    }

    @Override
    public void update(Results model) throws BusinessException {
        repository.deleteById(model.getId());
        repository.save(model);
    }
}
