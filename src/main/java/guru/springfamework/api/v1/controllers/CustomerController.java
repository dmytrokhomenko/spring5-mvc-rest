package guru.springfamework.api.v1.controllers;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.CustomerListDTO;
import guru.springfamework.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Dmytro.Khomenko on 15.01.2020.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {

  private final CustomerService customerService;

  @GetMapping("bynameandlastname")
  public ResponseEntity<CustomerDTO> getCustomerByFirstNameAndLastName(@RequestParam String firstname, @RequestParam String lastname) {
    return new ResponseEntity<>(customerService.getCustomerByFirstNameAndLastName(firstname, lastname), HttpStatus.OK);
  }

  @GetMapping()
  public ResponseEntity<CustomerListDTO> getAllCustomers() {
    CustomerListDTO customerListDTO = new CustomerListDTO();
    customerListDTO.setCustomers(customerService.getAllCustomers());

    return new ResponseEntity<>(customerListDTO, HttpStatus.OK);
  }

}


