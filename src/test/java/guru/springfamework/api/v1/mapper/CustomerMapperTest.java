package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Dmytro.Khomenko on 14.01.2020.
 */
class CustomerMapperTest {

  public static final String VICTOR = "Victor";
  public static final String FRANKL = "Frankl";
  public static final long ID = 124L;

  CustomerMapper customerMapper = CustomerMapper.INSTANCE;

  @Test
  void categoryToCategoryDTO() {

    //given
    Customer customer = new Customer();
    customer.setFirstName(VICTOR);
    customer.setLastName(FRANKL);
    customer.setId(ID);

    //when
    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

    //then
    assertEquals(ID, customerDTO.getId());
    assertEquals(VICTOR, customerDTO.getFirstname());
    assertEquals(FRANKL, customerDTO.getLastname());
  }

}