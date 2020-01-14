package guru.springfamework.repositories;

import guru.springfamework.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Dmytro.Khomenko on 14.01.2020.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

  Customer getCustomerByFirstNameAndLastName(String firstName, String lustName);

}
