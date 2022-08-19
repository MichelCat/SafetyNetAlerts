package io.swagger.dao.db.entities;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MedicalRecordMedicationEntityTest {

  private static MedicalRecordMedicationEntity medicalRecordMedicationEntity;

  @BeforeEach
  private void setUpPerTest() {
    medicalRecordMedicationEntity = new MedicalRecordMedicationEntity();
  }

  // -----------------------------------------------------------------------------------------------
  // Method getIdMedication
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Read null ID medication")
  public void getIdMedication_nullIdMedication_returnNull() {
    // GIVEN
    // WHEN
    // THEN
    assertThat(medicalRecordMedicationEntity.getIdMedication()).isNull();
  }

  // -----------------------------------------------------------------------------------------------
  // Method setIdMedication
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Write ID medication")
  public void setIdMedication_writeIdMedication() {
    // GIVEN
    // WHEN
    medicalRecordMedicationEntity.setIdMedication(1);
    // THEN
    assertThat(medicalRecordMedicationEntity.getIdMedication()).isEqualTo(1);
  }

  // -----------------------------------------------------------------------------------------------
  // Method getDosage
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Read null dosage")
  public void getDosage_nullDosage_returnNull() {
    // GIVEN
    // WHEN
    // THEN
    assertThat(medicalRecordMedicationEntity.getDosage()).isNull();
  }

  // -----------------------------------------------------------------------------------------------
  // Method setDosage
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Write dosage")
  public void setDosage_writeDosage() {
    // GIVEN
    // WHEN
    medicalRecordMedicationEntity.setDosage("350mg");
    // THEN
    assertThat(medicalRecordMedicationEntity.getDosage()).isEqualTo("350mg");
  }
}
