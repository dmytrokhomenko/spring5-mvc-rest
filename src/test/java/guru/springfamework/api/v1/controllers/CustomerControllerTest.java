package guru.springfamework.api.v1.controllers;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Dmytro.Khomenko on 15.01.2020.
 */
public class CustomerControllerTest {

  public static final String FIRST_NAME = "Boris";
  public static final String LAST_NAME = "LastName";
  public static final Long FRST_ID = 12L;

  @Mock
  CustomerService customerService;

  @InjectMocks
  CustomerController customerController;

  MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);

    mockMvc = MockMvcBuilders.standaloneSetup(customerController)
      .build();
  }

  @Test
  void getAllCustomers() throws Exception {
    //given
    List<CustomerDTO> customerDTOS = Arrays.asList(new CustomerDTO(), new CustomerDTO());

    when(customerService.getAllCustomers()).thenReturn(customerDTOS);

    //when
    mockMvc.perform(get("/api/v1/customers/").contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.customers", hasSize(2)));

  }

  @Test
  void getCustomerByFirstNameAndLastName() throws Exception {
    //given
    CustomerDTO someDTO = new CustomerDTO();
    someDTO.setFirstname(FIRST_NAME);
    someDTO.setLastname(LAST_NAME);
    someDTO.setId(FRST_ID);

    when(customerService.getCustomerByFirstNameAndLastName(anyString(), anyString())).thenReturn(someDTO);

    //when
    mockMvc.perform(get("/api/v1/customers/bynameandlastname").param("firstname", "name")
      .param("lastname", "last")
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.firstname", equalTo(FIRST_NAME)))
      .andExpect(jsonPath("$.lastname", equalTo(LAST_NAME)))
      .andExpect(jsonPath("$.id", equalTo(FRST_ID), Long.class));

  }

}