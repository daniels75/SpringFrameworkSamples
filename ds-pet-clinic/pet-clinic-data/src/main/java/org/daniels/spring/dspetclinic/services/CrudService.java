package org.daniels.spring.dspetclinic.services;

import java.util.Set;

public interface CrudService<T, ID> {
    Set<T> findAll();
    T save(T obj);
    T findById(ID id);
    void delete(T obj);
}
