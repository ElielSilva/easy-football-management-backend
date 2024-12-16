package com.easyfootballmanagement.Services;


import com.easyfootballmanagement.Exception.BusinessException;
import com.easyfootballmanagement.Models.Users;
import com.easyfootballmanagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService implements IService<Users> {

    @Autowired
    private UserRepository repository;

    @Override
    public List<Users> get() {
        return repository.findAll();
    }

    @Override
    public Users getById(long id) throws BusinessException {
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
    public void insert(Users model) throws BusinessException {
        repository.save(model);
    }

    @Override
    public void update(Users model) throws BusinessException {
        repository.deleteById(model.getId());
        repository.save(model);
    }
}
