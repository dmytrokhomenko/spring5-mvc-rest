package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CategoryMapper;
import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Dmytro.Khomenko on 14.01.2020.
 */
@Component
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final CategoryMapper categoryMapper;

  @Override
  public List<CategoryDTO> getAllCategories() {
    return categoryRepository.findAll()
      .stream()
      .map(categoryMapper::categoryToCategoryDTO)
      .collect(Collectors.toList());
  }

  @Override
  public CategoryDTO getCategoryByName(String name) {
    return categoryMapper.categoryToCategoryDTO(categoryRepository.getCategoryByName(name));
  }

}
