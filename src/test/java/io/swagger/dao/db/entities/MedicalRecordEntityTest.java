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
public class MedicalRecordEntityTest {

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
  public void getIdPerson_nullIdPerson_returnNull() {
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
  public void setIdPerson_writeIdPerson() {
    // GIVEN
    // WHEN
    medicalRecordEntity.setIdPerson(1);
    // THEN
    assertThat(medicalRecordEntity.getIdPerson()).isEqualTo(1);
  }

  // -----------------------------------------------------------------------------------------------
  // Method getFirstName
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Read null firstName")
  public void getFirstName_nullFirstName_returnNull() {
    // GIVEN
    // WHEN
    // THEN
    assertThat(medicalRecordEntity.getFirstName()).isNull();
  }

  // -----------------------------------------------------------------------------------------------
  // Method setFirstName
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Write firstName")
  public void setFirstName_writeFirstName() {
    // GIVEN
    // WHEN
    medicalRecordEntity.setFirstName("John");
    // THEN
    assertThat(medicalRecordEntity.getFirstName()).isEqualTo("John");
  }

  // -----------------------------------------------------------------------------------------------
  // Method getLastName
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Read null lastName")
  public void getLastName_nullLastName_returnNull() {
    // GIVEN
    // WHEN
    // THEN
    assertThat(medicalRecordEntity.getLastName()).isNull();
  }

  // -----------------------------------------------------------------------------------------------
  // Method setLastName
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Write lastName")
  public void setLastName_writeLastName() {
    // GIVEN
    // WHEN
    medicalRecordEntity.setLastName("Boyd");
    // THEN
    assertThat(medicalRecordEntity.getLastName()).isEqualTo("Boyd");
  }

  // -----------------------------------------------------------------------------------------------
  // Method getAllergies
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Read null Allergy list")
  public void getAllergies_nullAllergies_returnNull() {
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
  public void setAllergies_writeAllergies() {
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
  public void getMedications_nullMedications_returnNull() {
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
  public void setMedications_writeMedications() {
    // GIVEN
    // WHEN
    medicalRecordEntity.setMedications(medicationList);
    // THEN
    assertThat(medicalRecordEntity.getMedications()).isEqualTo(medicationList);
  }
}
