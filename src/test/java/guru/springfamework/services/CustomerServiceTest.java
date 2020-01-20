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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Created by Dmytro.Khomenko on 14.01.2020.
 */
class CustomerServiceTest {

  private static final Long ID = 32L;
  private static final String FIRST_NAME = "Boris";
  private static final String LAST_NAME = "Patron";


  @Mock
  CustomerRepository customerRepository;

  private CustomerService customerService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);

    customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
  }

  @Test
  void getAllCustomers() {
    //given
    List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());

    when(customerRepository.findAll()).thenReturn(customers);

    //when
    List<CustomerDTO> categoryDTOs = customerService.getAllCustomers();

    //then
    assertEquals(3, categoryDTOs.size());
  }

  @Test
  void getCustomerById() throws RuntimeException {
    //given
    Customer customer = new Customer();
    customer.setId(ID);
    customer.setFirstName(FIRST_NAME);
    customer.setLastName(LAST_NAME);

    Optional<Customer> optionalCustomer = Optional.of(customer);

    when(customerRepository.findById(anyLong())).thenReturn(optionalCustomer);

    //when
    CustomerDTO customerDTO = customerService.getCustomerById(ID);

    //then
    assertEquals(FIRST_NAME, customerDTO.getFirstname());
    assertEquals(LAST_NAME, customerDTO.getLastname());
    assertEquals("/api/v1/customers/" + ID, customerDTO.getUrl());
  }

  @Test
  void getCustomerByFirstNameAndLastName() throws RuntimeException {
    //given
    Customer customer = new Customer();
    customer.setId(ID);
    customer.setFirstName(FIRST_NAME);
    customer.setLastName(LAST_NAME);

    List<Customer> customers = Arrays.asList(customer, new Customer(), new Customer());

    Optional<List<Customer>> optionalCustomer = Optional.of(customers);

    when(customerRepository.getCustomerByFirstNameAndLastName(FIRST_NAME, LAST_NAME)).thenReturn(optionalCustomer);

    //when
    List<CustomerDTO> customerList = customerService.getCustomerByFirstNameAndLastName(FIRST_NAME, LAST_NAME);

    //then
    assertEquals(3, customerList.size());
    assertEquals(FIRST_NAME, customerList.get(0)
      .getFirstname());
    assertEquals(LAST_NAME, customerList.get(0)
      .getLastname());
    assertEquals("/api/v1/customers/" + ID, customerList.get(0)
      .getUrl());

  }

  @Test
  void createCustomerTest() {
    //given
    CustomerDTO customerDTO = new CustomerDTO();
    customerDTO.setFirstname(FIRST_NAME);
    customerDTO.setLastname(LAST_NAME);

    Customer customer = new Customer();
    customer.setId(ID);
    customer.setFirstName(FIRST_NAME);
    customer.setLastName(LAST_NAME);

    when(customerRepository.save(any())).thenReturn(customer);

    //when
    CustomerDTO customerDTOafterSave = customerService.createNewCustomer(customerDTO);

    //then
    assertEquals(FIRST_NAME, customerDTOafterSave.getFirstname());
    assertEquals(LAST_NAME, customerDTOafterSave.getLastname());
    assertEquals("/api/v1/customers/" + ID, customerDTOafterSave.getUrl());
  }

  @Test
  void saveCustomerByDTO() {
    //given
    CustomerDTO customerDTO = new CustomerDTO();
    customerDTO.setFirstname(FIRST_NAME);
    customerDTO.setLastname(LAST_NAME);

    Customer customer = new Customer();
    customer.setId(ID);
    customer.setFirstName(FIRST_NAME);
    customer.setLastName(LAST_NAME);

    when(customerRepository.save(any(Customer.class))).thenReturn(customer);

    //when
    CustomerDTO savedDto = customerService.saveCustomerByDTO(1L, customerDTO);

    //then
    assertEquals(customerDTO.getFirstname(), savedDto.getFirstname());
    assertEquals("/api/v1/customers/" + ID, savedDto.getUrl());
  }

  @Test
  void deleteById() {
    customerService.deleteCustomerById(12L);
    verify(customerRepository, times(1)).deleteById(12L);
  }


}
