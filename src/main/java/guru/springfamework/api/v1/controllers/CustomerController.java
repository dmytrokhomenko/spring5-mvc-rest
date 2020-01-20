package guru.springfamework.api.v1.controllers;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.CustomerListDTO;
import guru.springfamework.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Dmytro.Khomenko on 15.01.2020.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {

  public static final String CUSTOMERS_BASE_URL = "/api/v1/customers/";
  private final CustomerService customerService;

  @GetMapping("/{id}")
  public ResponseEntity<CustomerDTO> getCustomerByFirstNameAndLastName(@PathVariable Long id) throws RuntimeException {
    return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
  }

  @GetMapping("/bynameandlastname")
  public ResponseEntity<CustomerListDTO> getCustomerByFirstNameAndLastName(@RequestParam String firstname, @RequestParam String lastname) throws
    RuntimeException {
    CustomerListDTO customerListDTO = new CustomerListDTO();
    customerListDTO.setCustomers(customerService.getCustomerByFirstNameAndLastName(firstname, lastname));
    return new ResponseEntity<>(customerListDTO, HttpStatus.OK);
  }

  @GetMapping()
  public ResponseEntity<CustomerListDTO> getAllCustomers() {
    CustomerListDTO customerListDTO = new CustomerListDTO();
    customerListDTO.setCustomers(customerService.getAllCustomers());

    return new ResponseEntity<>(customerListDTO, HttpStatus.OK);
  }

  @PostMapping()
  public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO) {
    CustomerDTO createdCustomerDTO = customerService.createNewCustomer(customerDTO);
    return new ResponseEntity<>(createdCustomerDTO, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CustomerDTO> updateCustomerByDTO(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
    CustomerDTO createdCustomerDTO = customerService.saveCustomerByDTO(id, customerDTO);
    return new ResponseEntity<>(createdCustomerDTO, HttpStatus.OK);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<CustomerDTO> patchCustomerByDTO(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
    CustomerDTO updatedCustomer = customerService.patchCustomer(id, customerDTO);
    return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Long> deleteCustomerById(@PathVariable Long id) {
    customerService.deleteCustomerById(id);
    return new ResponseEntity<>(id, HttpStatus.OK);
  }
}


