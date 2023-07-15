package com.goan.football.utils;

import com.goan.football.models.Search;

import java.util.List;

public interface BaseService<T> {

    T save(T entity);

    T update(T entity);

    T get(String id);

    List<T>  all(Search search);

    List<T>  all(String id);

}
