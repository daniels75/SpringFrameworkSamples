package org.daniels.spring.dspetclinic.services.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractMapService<T, ID> {

    protected Map<ID, T> map = new HashMap<>();

    public Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    public T save(ID id, T obj) {
        return map.put(id, obj);
    }

    public T findById(ID id) {
        return map.get(id);
    }

    public void deleteByID(ID id) {
        map.remove(id);
    }
    public void delete(T obj) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(obj));
    }
}
