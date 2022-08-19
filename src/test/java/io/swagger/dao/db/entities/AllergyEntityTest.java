package io.swagger.dao.db.entities;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AllergyEntityTest {

  private static AllergyEntity allergyEntity;

  @BeforeEach
  private void setUpPerTest() {
    allergyEntity = new AllergyEntity();
  }

  // -----------------------------------------------------------------------------------------------
  // Method getId
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Read null ID allergy")
  public void getId_nullId_returnNull() {
    // GIVEN
    // WHEN
    // THEN
    assertThat(allergyEntity.getId()).isNull();
  }

  // -----------------------------------------------------------------------------------------------
  // Method setId
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Write ID allergy")
  public void setId_writeId() {
    // GIVEN
    // WHEN
    allergyEntity.setId(1);
    // THEN
    assertThat(allergyEntity.getId()).isEqualTo(1);
  }

  // -----------------------------------------------------------------------------------------------
  // Method getAllergy
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Read null allergy")
  public void getAllergy_nullAllergy_returnNull() {
    // GIVEN
    // WHEN
    // THEN
    assertThat(allergyEntity.getAllergy()).isNull();
  }

  // -----------------------------------------------------------------------------------------------
  // Method setAllergy
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Write allergy")
  public void setAllergy_writeAllergy() {
    // GIVEN
    // WHEN
    allergyEntity.setAllergy("nillacilan");
    // THEN
    assertThat(allergyEntity.getAllergy()).isEqualTo("nillacilan");
  }
}
