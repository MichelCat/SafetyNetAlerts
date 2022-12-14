package com.safetynet.safetynetalerts.dao.db;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.safetynet.safetynetalerts.dao.DataBasePrepareBusiness;
import com.safetynet.safetynetalerts.dao.db.entities.AllergyEntity;
import com.safetynet.safetynetalerts.data.MickBoydData;

/**
 * AllergyDaoIT is the integration test class managing the AllergyEntity list
 * 
 * @author MC
 * @version 1.0
 */
@SpringBootTest
class AllergyDaoIT {

  @Autowired
  private AllergyDao allergyDao;
  @Autowired
  private DataBasePrepareBusiness dataBasePrepareService;
  
  private static AllergyEntity mickAllergyEntityNillacilan;

  @BeforeEach
  private void setUpPerTest() {
    dataBasePrepareService.clearDataBase();
    mickAllergyEntityNillacilan = MickBoydData.getAllergyEntityNillacilan();
  }

  // -----------------------------------------------------------------------------------------------
  // Method clearTable
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, Clear AllergyEntity list
   */
  @Test
  void clearTable_Normal() {
    // GIVEN
    allergyDao.save(mickAllergyEntityNillacilan);
    // WHEN
    allergyDao.clearTable();
    // THEN
    assertThat(allergyDao.allergyById(mickAllergyEntityNillacilan.getId())).isNull();
  }

  // -----------------------------------------------------------------------------------------------
  // Method allergyById
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, Allergy search by allergy ID
   */
  @Test
  void allergyById_Normal() {
    // GIVEN
    allergyDao.save(mickAllergyEntityNillacilan);
    // WHEN
    assertThat(allergyDao.allergyById(mickAllergyEntityNillacilan.getId())).isEqualTo(mickAllergyEntityNillacilan);
    // THEN
  }
  
  /**
   * Borderline case test, empty list, Allergy search by allergy ID
   */
  @Test
  void allergyById_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(allergyDao.allergyById(mickAllergyEntityNillacilan.getId())).isNull();
    // THEN
  }

  // -----------------------------------------------------------------------------------------------
  // Method findIdAllergyByName
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, Allergy search by allergy name
   */
  @Test
  void findIdAllergyByName_Normal() {
    // GIVEN
    allergyDao.save(mickAllergyEntityNillacilan);
    // WHEN
    assertThat(allergyDao.findIdAllergyByName(mickAllergyEntityNillacilan.getAllergy())).isEqualTo(mickAllergyEntityNillacilan);
    // THEN
  }
  
  /**
   * Borderline case test, empty list, Allergy search by allergy name
   */
  @Test
  void findIdAllergyByName_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(allergyDao.findIdAllergyByName(mickAllergyEntityNillacilan.getAllergy())).isNull();
    // THEN
  }

  // -----------------------------------------------------------------------------------------------
  // Method save
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, Add a allergy
   */
  @Test
  void save_Normal() {
    // GIVEN
    // WHEN
    AllergyEntity result = allergyDao.save(mickAllergyEntityNillacilan);
    // THEN
    assertThat(result).isEqualTo(mickAllergyEntityNillacilan);
    assertThat(result).isEqualTo(allergyDao.allergyById(result.getId()));
  }
  
  /**
   * Borderline case test, record already created, Add a allergy
   */
  @Test
  void save_recordingPresent() {
    // GIVEN
    allergyDao.save(mickAllergyEntityNillacilan);
    // WHEN
    assertThat(allergyDao.save(mickAllergyEntityNillacilan)).isNull();
    // THEN
    assertThat(mickAllergyEntityNillacilan).isEqualTo(allergyDao.allergyById(mickAllergyEntityNillacilan.getId()));
  }
}
