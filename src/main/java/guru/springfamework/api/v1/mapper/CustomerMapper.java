package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Created by Dmytro.Khomenko on 14.01.2020.
 */
@Mapper
public interface CustomerMapper {

  CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

  @Mapping(target = "url", ignore = true)
  @Mapping(source = "firstName", target = "firstname")
  @Mapping(source = "lastName", target = "lastname")
  CustomerDTO customerToCustomerDTO(Customer customer);

  @Mapping(target = "id", ignore = true)
  @Mapping(source = "firstname", target = "firstName")
  @Mapping(source = "lastname", target = "lastName")
  Customer customerDTOToCustomer(CustomerDTO customerDTO);

}
