package org.daniels.spring.dspetclinic.services;

import org.daniels.spring.dspetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>{

    Owner findByLastName(String lastName);
}
