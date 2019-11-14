package org.daniels.spring.dspetclinic.services.map;

import org.daniels.spring.dspetclinic.model.Vet;
import org.daniels.spring.dspetclinic.services.CrudService;

import java.util.Set;

public class VetServiceMap extends AbstractMapService<Vet, Long> implements CrudService<Vet, Long> {

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet save(Vet obj) {
        return super.save(obj.getId(), obj);
    }

    @Override
    public Vet save(Long aLong, Vet obj) {
        return super.save(aLong, obj);
    }

    @Override
    public Vet findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public void deleteByID(Long aLong) {
        super.deleteByID(aLong);
    }

    @Override
    public void delete(Vet obj) {
        super.delete(obj);
    }
}
