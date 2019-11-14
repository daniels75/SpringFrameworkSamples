package org.daniels.spring.dspetclinic.services.map;

import org.daniels.spring.dspetclinic.model.Owner;
import org.daniels.spring.dspetclinic.services.CrudService;

import java.util.Set;

public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements CrudService<Owner, Long> {

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner save(Owner obj) {
        return super.save(obj.getId(), obj);
    }

    @Override
    public Owner save(Long aLong, Owner obj) {
        return super.save(aLong, obj);
    }

    @Override
    public Owner findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public void deleteByID(Long aLong) {
        super.deleteByID(aLong);
    }

    @Override
    public void delete(Owner obj) {
        super.delete(obj);
    }
}
