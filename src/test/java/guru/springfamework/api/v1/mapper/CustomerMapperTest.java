package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Dmytro.Khomenko on 14.01.2020.
 */
class CustomerMapperTest {

  private static final String VICTOR = "Victor";
  private static final String FRANKL = "Frankl";
  private static final long ID = 124L;

  private CustomerMapper customerMapper = CustomerMapper.INSTANCE;

  @Test
  void customerToCustomerDTO() {

    //given
    Customer customer = new Customer();
    customer.setFirstName(VICTOR);
    customer.setLastName(FRANKL);

    //when
    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

    //then
    assertEquals(VICTOR, customerDTO.getFirstname());
    assertEquals(FRANKL, customerDTO.getLastname());
  }

  @Test
  void customerDTOToCustomer() {
    //given
    CustomerDTO customerDTO = new CustomerDTO();
    customerDTO.setFirstname(VICTOR);
    customerDTO.setLastname(FRANKL);

    //when
    Customer customer = customerMapper.customerDTOToCustomer(customerDTO);

    //then
    assertEquals(VICTOR, customer.getFirstName());
    assertEquals(FRANKL, customer.getLastName());
  }


}