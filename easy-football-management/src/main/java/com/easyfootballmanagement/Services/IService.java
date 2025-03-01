package com.easyfootballmanagement.Services;

import com.easyfootballmanagement.application.common.exception.BusinessException;

import java.util.List;

public interface IService<T> {
    List<T> get();
    T getById(long id) throws BusinessException;
    void delete(long id) throws BusinessException;
    void insert(T model) throws BusinessException;
    void update(T model) throws BusinessException;
}
