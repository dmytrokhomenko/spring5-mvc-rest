package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Dmytro.Khomenko on 14.01.2020.
 */
class CategoryMapperTest {

  public static final String FRANKL = "Frankl";
  public static final long ID = 124L;

  CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

  @Test
  void categoryToCategoryDTO() {
    //given
    Category category = new Category();
    category.setName(FRANKL);
    category.setId(ID);

    //when
    CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

    //then
    assertEquals(ID, categoryDTO.getId());
    assertEquals(FRANKL, categoryDTO.getName());
  }

}