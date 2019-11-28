package org.daniels.spring.mvc.rest.services;


import org.daniels.spring.mvc.rest.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(Long id);
    CustomerDTO createNewCustomer(CustomerDTO customerDTO);
 CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO);
}
