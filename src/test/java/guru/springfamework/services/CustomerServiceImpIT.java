package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapperImpl;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.bootstrap.Bootstrap;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by Dmytro.Khomenko on 19.01.2020.
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CustomerServiceImpIT {

  private final CustomerRepository customerRepository;
  private final CategoryRepository categoryRepository;

  private CustomerService customerService;

  @Autowired
  public CustomerServiceImpIT(CustomerRepository customerRepository, CategoryRepository categoryRepository) {
    this.customerRepository = customerRepository;
    this.categoryRepository = categoryRepository;
  }

  @BeforeEach
  void setUp() {
    Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository);
    bootstrap.run();

    customerService = new CustomerServiceImpl(customerRepository, CustomerMapperImpl.INSTANCE);
  }

  @Test
  public void patchCustomerUpdateFirstName() {
    String updatedName = "UpdatedName";
    long id = getCustomerIdValue();

    Customer originalCustomer = customerRepository.getOne(id);
    assertNotNull(originalCustomer);
    //save original first name
    String originalFirstName = originalCustomer.getFirstName();
    String originalLastName = originalCustomer.getLastName();

    CustomerDTO customerDTO = new CustomerDTO();
    customerDTO.setFirstname(updatedName);

    customerService.patchCustomer(id, customerDTO);

    Optional<Customer> updatedCustomer = customerRepository.findById(id);

    updatedCustomer.ifPresent(customer -> {
      assertEquals(updatedName, customer.getFirstName());
      assertThat(originalFirstName, not(equalTo(customer.getFirstName())));
      assertThat(originalLastName, equalTo(customer.getLastName()));
    });

  }

  @Test
  public void patchCustomerUpdateLastName() {
    String updatedName = "UpdatedName";
    long id = getCustomerIdValue();

    Customer originalCustomer = customerRepository.getOne(id);
    assertNotNull(originalCustomer);

    //save original first/last name
    String originalFirstName = originalCustomer.getFirstName();
    String originalLastName = originalCustomer.getLastName();

    CustomerDTO customerDTO = new CustomerDTO();
    customerDTO.setLastname(updatedName);

    customerService.patchCustomer(id, customerDTO);

    Optional<Customer> updatedCustomer = customerRepository.findById(id);

    updatedCustomer.ifPresent(customer -> {
      assertEquals(updatedName, customer.getLastName());
      assertThat(originalFirstName, equalTo(customer.getFirstName()));
      assertThat(originalLastName, not(equalTo(customer.getFirstName())));
    });

  }

  private Long getCustomerIdValue() {
    List<Customer> customers = customerRepository.findAll();

    System.out.println("Customers Found: " + customers.size());

    //return first id
    return customers.get(0)
      .getId();
  }

}
