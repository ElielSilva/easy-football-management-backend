package com.easyfootballmanagement.Services;

import com.easyfootballmanagement.Exception.BusinessException;
import com.easyfootballmanagement.Models.Users;

import java.util.List;

public interface IService<T> {
    List<T> get();
    T getById(long id) throws BusinessException;
    void delete(long id) throws BusinessException;
    void insert(T model) throws BusinessException;
    void update(T model) throws BusinessException;
}
