package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
  public List<CustomerDTO> getCustomerByFirstNameAndLastName(String firstName, String lastName) throws RuntimeException {
    Optional<List<Customer>> customerOptional = customerRepository.getCustomerByFirstNameAndLastName(firstName, lastName);
    return customerOptional.map(customers -> customers.stream()
      .map(customer -> {
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
        customerDTO.setUrl("/api/v1/customers/" + customer.getId());
        return customerDTO;
      })
      .collect(Collectors.toList()))
      .orElseThrow(RuntimeException::new);
  }

  @Override
  public List<CustomerDTO> getAllCustomers() {
    return customerRepository.findAll()
      .stream()
      .map(customer -> {
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
        customerDTO.setUrl("/api/v1/customers/" + customer.getId());
        return customerDTO;
      })
      .collect(Collectors.toList());
  }

  @Override
  public CustomerDTO getCustomerById(Long id) throws RuntimeException {
    Optional<Customer> customerOptional = customerRepository.findById(id);
    return customerOptional.map(customer -> {
      CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
      customerDTO.setUrl("/api/v1/customers/" + customer.getId());
      return customerDTO;
    })
      .orElseThrow(RuntimeException::new);
  }

  @Override
  public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
    Customer customerAfterSave = customerRepository.save(customerMapper.customerDTOToCustomer(customerDTO));
    CustomerDTO customerDTOAfterSave = customerMapper.customerToCustomerDTO(customerAfterSave);
    customerDTOAfterSave.setUrl("/api/v1/customers/" + customerAfterSave.getId());
    return customerDTOAfterSave;
  }

  @Override
  public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
    Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
    customer.setId(id);
    return saveAndReturnDTO(customer);
  }

  private CustomerDTO saveAndReturnDTO(Customer customer) {
    Customer savedCustomer = customerRepository.save(customer);
    CustomerDTO returnDto = customerMapper.customerToCustomerDTO(savedCustomer);
    returnDto.setUrl("/api/v1/customers/" + savedCustomer.getId());
    return returnDto;
  }


  @Override
  public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
    return customerRepository.findById(id)
      .map(customer -> {

        if (customerDTO.getFirstname() != null) {
          customer.setFirstName(customerDTO.getFirstname());
        }

        if (customerDTO.getLastname() != null) {
          customer.setLastName(customerDTO.getLastname());
        }

        return customerMapper.customerToCustomerDTO(customerRepository.save(customer));
      })
      .orElseThrow(RuntimeException::new);
  }

}
