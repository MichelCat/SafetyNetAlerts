package io.swagger.dao.db.entities;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MedicalRecordAllergyEntityTest {

  private static MedicalRecordAllergyEntity medicalRecordAllergyEntity;

  @BeforeEach
  private void setUpPerTest() {
    medicalRecordAllergyEntity = new MedicalRecordAllergyEntity();
  }

  // -----------------------------------------------------------------------------------------------
  // Method getIdAlergy
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Read null ID Alergy")
  public void getIdAlergy_nullIdAlergy_returnNull() {
    // GIVEN
    // WHEN
    // THEN
    assertThat(medicalRecordAllergyEntity.getIdAlergy()).isNull();
  }

  // -----------------------------------------------------------------------------------------------
  // Method setIdAlergy
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Write ID Alergy")
  public void setIdAlergy_writeIdAlergy() {
    // GIVEN
    // WHEN
    medicalRecordAllergyEntity.setIdAlergy(1);
    // THEN
    assertThat(medicalRecordAllergyEntity.getIdAlergy()).isEqualTo(1);
  }
}
