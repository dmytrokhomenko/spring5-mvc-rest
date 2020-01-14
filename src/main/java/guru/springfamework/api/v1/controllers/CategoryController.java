package guru.springfamework.api.v1.controllers;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.api.v1.model.CategoryListDTO;
import guru.springfamework.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Dmytro.Khomenko on 14.01.2020.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping("{name}")
  public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name) {
    return new ResponseEntity<>(categoryService.getCategoryByName(name), HttpStatus.OK);
  }

  @GetMapping()
  public ResponseEntity<CategoryListDTO> getAllCategory() {
    CategoryListDTO categoryListDTO = new CategoryListDTO();
    categoryListDTO.setCategories(categoryService.getAllCategories());

    return new ResponseEntity<>(categoryListDTO, HttpStatus.OK);
  }

}
