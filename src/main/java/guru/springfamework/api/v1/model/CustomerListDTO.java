package guru.springfamework.api.v1.model;

import lombok.Data;

import java.util.List;

/**
 * Created by Dmytro.Khomenko on 15.01.2020.
 */
@Data
public class CustomerListDTO {

  List<CustomerDTO> customers;

}
