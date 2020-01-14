package guru.springfamework.api.v1.model;

import lombok.Data;

import java.util.List;

/**
 * Created by Dmytro.Khomenko on 14.01.2020.
 */
@Data
public class CategoryListDTO {

  List<CategoryDTO> categories;

}
