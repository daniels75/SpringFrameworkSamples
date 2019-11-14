package org.daniels.spring.dspetclinic.services.map;

import org.daniels.spring.dspetclinic.model.Pet;
import org.daniels.spring.dspetclinic.services.CrudService;

import java.util.Set;

public class PetServiceMap extends AbstractMapService<Pet, Long> implements CrudService<Pet, Long> {

    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public Pet save(Pet obj) {
        return super.save(obj.getId(), obj);
    }

    @Override
    public Pet save(Long aLong, Pet obj) {
        return super.save(aLong, obj);
    }

    @Override
    public Pet findById(Long aLong) {
        return super.findById(aLong);
    }

    @Override
    public void deleteByID(Long aLong) {
        super.deleteByID(aLong);
    }

    @Override
    public void delete(Pet obj) {
        super.delete(obj);
    }
}
