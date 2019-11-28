package org.daniels.spring.mvc.rest.api.v1.mapper;

import org.daniels.spring.mvc.rest.api.v1.model.CustomerDTO;
import org.daniels.spring.mvc.rest.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);
    Customer customerDtoToCustomer(CustomerDTO customerDTO);
}
