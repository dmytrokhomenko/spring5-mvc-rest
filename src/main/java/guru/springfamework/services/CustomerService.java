package guru.springfamework.services;

import guru.springfamework.api.v1.model.CustomerDTO;

import java.util.List;

/**
 * Created by Dmytro.Khomenko on 14.01.2020.
 */
public interface CustomerService {

  CustomerDTO getCustomerByFirstNameAndLastName(String firstName, String lastName);

  List<CustomerDTO> getAllCustomers();

}
