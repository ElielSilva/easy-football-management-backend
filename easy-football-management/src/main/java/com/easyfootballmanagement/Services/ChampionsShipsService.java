package com.easyfootballmanagement.Services;

import com.easyfootballmanagement.application.common.exception.BusinessException;
import com.easyfootballmanagement.domain.entities.ChampionsShips;
import com.easyfootballmanagement.infrastructure.repository.ChampionsShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChampionsShipsService implements IService<ChampionsShips> {

    @Autowired
    private ChampionsShipRepository repository;

    @Override
    public List<ChampionsShips> get() {
        return repository.findAll();
    }

    @Override
    public ChampionsShips getById(long id) throws BusinessException {
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
    public void insert(ChampionsShips model) throws BusinessException {
        repository.save(model);
    }

    @Override
    public void update(ChampionsShips model) throws BusinessException {
        System.out.println("ok");
    }
}
