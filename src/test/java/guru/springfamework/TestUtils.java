package guru.springfamework;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Dmytro.Khomenko on 18.01.2020.
 */
public class TestUtils {

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
