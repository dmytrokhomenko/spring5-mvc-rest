package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CategoryMapper;
import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;
import guru.springfamework.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Dmytro.Khomenko on 14.01.2020.
 */
class CategoryServiceTest {

  public static final Long ID = 32L;
  public static final String NAME = "Boris";

  @Mock
  CategoryRepository categoryRepository;

  CategoryService categoryService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);

    categoryService = new CategoryServiceImpl(categoryRepository, CategoryMapper.INSTANCE);
  }

  @Test
  void getAllCategories() {
    //given
    List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());

    when(categoryRepository.findAll()).thenReturn(categories);

    //when
    List<CategoryDTO> categoryDTOs = categoryService.getAllCategories();

    //then
    assertEquals(3, categoryDTOs.size());
  }

  @Test
  void getCategoryByName() {
    //given
    Category category = new Category();
    category.setId(ID);
    category.setName(NAME);

    when(categoryRepository.getCategoryByName(NAME)).thenReturn(category);

    //when
    CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);

    //then
    assertEquals(NAME, categoryDTO.getName());
    assertEquals(ID, categoryDTO.getId());

  }

}