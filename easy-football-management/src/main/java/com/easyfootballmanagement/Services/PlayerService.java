package com.easyfootballmanagement.Services;

import com.easyfootballmanagement.application.common.exception.BusinessException;
import com.easyfootballmanagement.domain.entities.Players;
import com.easyfootballmanagement.infrastructure.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService implements IService<Players> {

    @Autowired
    private PlayerRepository repository;

    @Override
    public List<Players> get() {
        return repository.findAll();
    }

    @Override
    public Players getById(long id) throws BusinessException {
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
    public void insert(Players model) throws BusinessException {
        repository.save(model);
    }

    @Override
    public void update(Players model) throws BusinessException {
        System.out.println("ok");
    }
}
