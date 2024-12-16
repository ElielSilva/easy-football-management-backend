package com.easyfootballmanagement.Services;

import com.easyfootballmanagement.Exception.BusinessException;
import com.easyfootballmanagement.Models.ChampionsShipsHasTeams;
import com.easyfootballmanagement.Repository.ChampionsShipsHasTeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChampionsShipsHasTeamService implements IService<ChampionsShipsHasTeams> {

    @Autowired
    private ChampionsShipsHasTeamsRepository repository;

    @Override
    public List<ChampionsShipsHasTeams> get() {
        return repository.findAll();
    }

    @Override
    public ChampionsShipsHasTeams getById(long id) throws BusinessException {
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
    public void insert(ChampionsShipsHasTeams model) throws BusinessException {
        repository.save(model);
    }

    @Override
    public void update(ChampionsShipsHasTeams model) throws BusinessException {
        repository.deleteById(model.getId());
        repository.save(model);
    }
}
