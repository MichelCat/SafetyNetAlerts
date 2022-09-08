package io.swagger.dao;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.TreeSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import io.swagger.dao.SafetyNetDataBase;
import io.swagger.dao.db.entities.AllergyEntity;
import io.swagger.dao.db.entities.FireStationEntity;
import io.swagger.dao.db.entities.MedicalRecordEntity;
import io.swagger.dao.db.entities.MedicationEntity;
import io.swagger.dao.db.entities.PersonEntity;

@SpringBootTest
public class SafetyNetDataBaseTest {

  private static SafetyNetDataBase safetyNetDataBase;
  
//  @BeforeEach
//  private void setUpPerTest() {
//    safetyNetDataBase = new SafetyNetDataBase();
//  }
//
//  // -----------------------------------------------------------------------------------------------
//  // Method getPersonEntities
//  // -----------------------------------------------------------------------------------------------
//  @Test
//  @DisplayName("Read null PersonEntity list")
//  public void getPersonEntities_nullPersonEntities_returnNull() {
//    // GIVEN
//    // WHEN
//    // THEN
//    assertThat(safetyNetDataBase.getPersonEntities()).isEqualTo(new TreeSet<PersonEntity>());
//  }
//
//  // -----------------------------------------------------------------------------------------------
//  // Method getFireStationEntities
//  // -----------------------------------------------------------------------------------------------
//  @Test
//  @DisplayName("Read null FireStationEntity list")
//  public void getFireStationEntities_nullStationEntities_returnNull() {
//    // GIVEN
//    // WHEN
//    // THEN
//    assertThat(safetyNetDataBase.getFireStationEntities()).isEqualTo(new TreeSet<FireStationEntity>());
//  }
//
//  // -----------------------------------------------------------------------------------------------
//  // Method getAllergyEntities
//  // -----------------------------------------------------------------------------------------------
//  @Test
//  @DisplayName("Read null AllergyEntity list")
//  public void getAllergyEntities_nullAllergyEntities_returnNull() {
//    // GIVEN
//    // WHEN
//    // THEN
//    assertThat(safetyNetDataBase.getAllergyEntities()).isEqualTo(new TreeSet<AllergyEntity>());
//  }
//
//  // -----------------------------------------------------------------------------------------------
//  // Method getMedicationEntities
//  // -----------------------------------------------------------------------------------------------
//  @Test
//  @DisplayName("Read null MedicationEntity list")
//  public void getMedicationEntities_nullMedicationEntities_returnNull() {
//    // GIVEN
//    // WHEN
//    // THEN
//    assertThat(safetyNetDataBase.getMedicationEntities()).isEqualTo(new TreeSet<MedicationEntity>());
//  }
//
//  // -----------------------------------------------------------------------------------------------
//  // Method getMedicalRecordEntities
//  // -----------------------------------------------------------------------------------------------
//  @Test
//  @DisplayName("Read null MedicalRecordEntity list")
//  public void getMedicalRecordEntities_nullMedicalRecordEntities_returnNull() {
//    // GIVEN
//    // WHEN
//    // THEN
//    assertThat(safetyNetDataBase.getMedicalRecordEntities()).isEqualTo(new TreeSet<MedicalRecordEntity>());
//  }
}
