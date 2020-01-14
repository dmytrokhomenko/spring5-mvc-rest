package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.repositories.CategoryRepository;
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

  @Override
  public void run(String... args) throws Exception {
    String[] categoriesName = {"Fruits", "Dried", "Fresh", "Exotic", "Nuts"};

    for (String name : categoriesName) {
      Category category = new Category();
      category.setName(name);
      categoryRepository.save(category);
    }

    log.info("Data loaded, quantity = " + categoryRepository.count());
  }

}
