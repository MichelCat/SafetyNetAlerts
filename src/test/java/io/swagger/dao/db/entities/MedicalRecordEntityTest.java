package io.swagger.dao.db.entities;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MedicalRecordEntityTest {

  private static MedicalRecordEntity medicalRecordEntity;
  private static List<MedicalRecordAllergyEntity> allergyList;
  private static List<MedicalRecordMedicationEntity> medicationList;

  @BeforeAll
  private static void setUp() throws Exception {
    allergyList = new ArrayList<>();
    medicationList = new ArrayList<>();
  }

  @BeforeEach
  private void setUpPerTest() {
    medicalRecordEntity = new MedicalRecordEntity();
  }

  // -----------------------------------------------------------------------------------------------
  // Method getIdPerson
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Read null ID person")
  void getIdPerson_nullIdPerson_returnNull() {
    // GIVEN
    // WHEN
    // THEN
    assertThat(medicalRecordEntity.getIdPerson()).isNull();
  }

  // -----------------------------------------------------------------------------------------------
  // Method setIdPerson
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Write ID person")
  void setIdPerson_writeIdPerson() {
    // GIVEN
    // WHEN
    medicalRecordEntity.setIdPerson(1);
    // THEN
    assertThat(medicalRecordEntity.getIdPerson()).isEqualTo(1);
  }

  // -----------------------------------------------------------------------------------------------
  // Method getAllergies
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Read null Allergy list")
  void getAllergies_nullAllergies_returnNull() {
    // GIVEN
    // WHEN
    // THEN
    assertThat(medicalRecordEntity.getAllergies()).isNull();
  }

  // -----------------------------------------------------------------------------------------------
  // Method setAllergies
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Write Allergy list")
  void setAllergies_writeAllergies() {
    // GIVEN
    // WHEN
    medicalRecordEntity.setAllergies(allergyList);
    // THEN
    assertThat(medicalRecordEntity.getAllergies()).isEqualTo(allergyList);
  }

  // -----------------------------------------------------------------------------------------------
  // Method getMedications
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Read null Medication list")
  void getMedications_nullMedications_returnNull() {
    // GIVEN
    // WHEN
    // THEN
    assertThat(medicalRecordEntity.getMedications()).isNull();
  }

  // -----------------------------------------------------------------------------------------------
  // Method setMedications
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Write Medication list")
  void setMedications_writeMedications() {
    // GIVEN
    // WHEN
    medicalRecordEntity.setMedications(medicationList);
    // THEN
    assertThat(medicalRecordEntity.getMedications()).isEqualTo(medicationList);
  }
}
