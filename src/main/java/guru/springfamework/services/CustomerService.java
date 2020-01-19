package guru.springfamework.services;

import guru.springfamework.api.v1.model.CustomerDTO;

import java.util.List;

/**
 * Created by Dmytro.Khomenko on 14.01.2020.
 */
public interface CustomerService {

  List<CustomerDTO> getCustomerByFirstNameAndLastName(String firstName, String lastName) throws RuntimeException;

  List<CustomerDTO> getAllCustomers();

  CustomerDTO getCustomerById(Long id) throws RuntimeException;

  CustomerDTO createNewCustomer(CustomerDTO customerDTO);

  CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO);

  CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);

}
