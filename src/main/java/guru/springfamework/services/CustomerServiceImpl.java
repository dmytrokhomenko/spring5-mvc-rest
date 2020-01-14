package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Dmytro.Khomenko on 14.01.2020.
 */
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  private final CustomerMapper customerMapper;

  @Override
  public CustomerDTO getCustomerByFirstNameAndLastName(String firstName, String lastName) {
    return customerMapper.customerToCustomerDTO(customerRepository.getCustomerByFirstNameAndLastName(firstName, lastName));
  }

  @Override
  public List<CustomerDTO> getAllCustomers() {
    return customerRepository.findAll()
      .stream()
      .map(customerMapper::customerToCustomerDTO)
      .collect(Collectors.toList());
  }

}
