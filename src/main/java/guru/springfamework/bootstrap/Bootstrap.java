package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * Created by Dmytro.Khomenko on 07.01.2020.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class Bootstrap implements CommandLineRunner {

  private final CategoryRepository categoryRepository;
  private final CustomerRepository customerRepository;

  @Override
  public void run(String... args) {
    String[] categoriesName = {"Fruits", "Dried", "Fresh", "Exotic", "Nuts"};

    for (String name : categoriesName) {
      Category category = new Category();
      category.setName(name);
      categoryRepository.save(category);
    }

    log.info("Data loaded, quantity = " + categoryRepository.count());

    String[][] customersName = {
      {"Patron", "Frankl", "Gomer"}, {"Boss", "Viktor", "Simpson"}
    };

    for (int i = 0; i < customersName[0].length; i++) {
      Customer customer = new Customer();
      customer.setFirstName(customersName[0][i]);
      customer.setLastName(customersName[1][i]);
      customerRepository.save(customer);
    }
  }

}
