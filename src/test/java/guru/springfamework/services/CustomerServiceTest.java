package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Dmytro.Khomenko on 14.01.2020.
 */
public class CustomerServiceTest {

  public static final Long ID = 32L;
  public static final String FIRST_NAME = "Boris";
  public static final String LAST_NAME = "Patron";


  @Mock
  CustomerRepository customerRepository;

  CustomerService customerService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);

    customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
  }

  @Test
  void getAllCategories() {
    //given
    List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());

    when(customerRepository.findAll()).thenReturn(customers);

    //when
    List<CustomerDTO> categoryDTOs = customerService.getAllCustomers();

    //then
    assertEquals(3, categoryDTOs.size());
  }

  @Test
  void getCustomerByFirstNameAndLastName() {
    //given
    Customer customer = new Customer();
    customer.setId(ID);
    customer.setFirstName(FIRST_NAME);
    customer.setLastName(LAST_NAME);

    when(customerRepository.getCustomerByFirstNameAndLastName(FIRST_NAME, LAST_NAME)).thenReturn(customer);

    //when
    CustomerDTO customerDTO = customerService.getCustomerByFirstNameAndLastName(FIRST_NAME, LAST_NAME);

    //then
    assertEquals(FIRST_NAME, customerDTO.getFirstname());
    assertEquals(LAST_NAME, customerDTO.getLastname());
    assertEquals(ID, customerDTO.getId());

  }

}
