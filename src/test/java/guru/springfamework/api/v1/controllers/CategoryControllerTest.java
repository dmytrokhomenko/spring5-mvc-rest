package guru.springfamework.api.v1.controllers;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Dmytro.Khomenko on 14.01.2020.
 */
class CategoryControllerTest {

  public static final String SOME = "Some category";
  public static final String WOW = "Wow category";
  public static final Long SOME_ID = 12L;
  public static final Long WOW_ID = 13L;

  @Mock
  CategoryService categoryService;

  @InjectMocks
  CategoryController categoryController;

  MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);

    mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
      .build();
  }

  @Test
  void getAllCategory() throws Exception {
    //given
    CategoryDTO someDTO = new CategoryDTO();
    someDTO.setName(SOME);
    someDTO.setId(SOME_ID);

    CategoryDTO wowDTO = new CategoryDTO();
    wowDTO.setId(WOW_ID);
    wowDTO.setName(WOW);

    List<CategoryDTO> categoryDTOS = Arrays.asList(someDTO, wowDTO);

    when(categoryService.getAllCategories()).thenReturn(categoryDTOS);

    //when
    mockMvc.perform(get("/api/v1/categories/").contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.categories", hasSize(2)));

  }

  @Test
  void getCategoryByName() throws Exception {
    //given
    CategoryDTO someDTO = new CategoryDTO();
    someDTO.setName(SOME);
    someDTO.setId(SOME_ID);

    when(categoryService.getCategoryByName(ArgumentMatchers.any())).thenReturn(someDTO);

    //when
    mockMvc.perform(get("/api/v1/categories/some").contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.name", equalTo(SOME)));

  }

}