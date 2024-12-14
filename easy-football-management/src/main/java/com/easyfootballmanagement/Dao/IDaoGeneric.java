package com.easyfootballmanagement.Dao;

import java.util.List;

public interface IDaoGeneric <T> {
    List<T> get();
    T getById(long id);
    void put(T model);
    void update(T model);
    void remove(long id);
}
