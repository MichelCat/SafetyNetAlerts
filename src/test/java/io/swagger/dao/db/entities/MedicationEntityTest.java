package io.swagger.dao.db.entities;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MedicationEntityTest {

  private static MedicationEntity medicationEntity;

  @BeforeEach
  private void setUpPerTest() {
    medicationEntity = new MedicationEntity();
  }

  // -----------------------------------------------------------------------------------------------
  // Method getId
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Read null ID medication")
  public void getId_nullId_returnNull() {
    // GIVEN
    // WHEN
    // THEN
    assertThat(medicationEntity.getId()).isNull();
  }

  // -----------------------------------------------------------------------------------------------
  // Method setId
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Write ID medication")
  public void setId_writeId() {
    // GIVEN
    // WHEN
    medicationEntity.setId(1);
    // THEN
    assertThat(medicationEntity.getId()).isEqualTo(1);
  }

  // -----------------------------------------------------------------------------------------------
  // Method getMedication
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Read null medication")
  public void getMedication_nullMedication_returnNull() {
    // GIVEN
    // WHEN
    // THEN
    assertThat(medicationEntity.getMedication()).isNull();
  }

  // -----------------------------------------------------------------------------------------------
  // Method setMedication
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Write medication")
  public void setMedication_writeMedication() {
    // GIVEN
    // WHEN
    medicationEntity.setMedication("aznol");
    // THEN
    assertThat(medicationEntity.getMedication()).isEqualTo("aznol");
  }
}
