package com.safetynet.safetynetalerts.business;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.safetynet.safetynetalerts.business.ChildAlertBusiness;
import com.safetynet.safetynetalerts.dao.DataBasePrepareBusiness;
import com.safetynet.safetynetalerts.dao.db.PersonDao;
import com.safetynet.safetynetalerts.dao.db.entities.PersonEntity;
import com.safetynet.safetynetalerts.data.MickBoydData;
import com.safetynet.safetynetalerts.data.YoungBoydData;
import com.safetynet.safetynetalerts.model.Person;

@SpringBootTest
class ChildAlertBusinessIT {
  
  @Autowired
  private ChildAlertBusiness childAlertBusiness;
  @Autowired
  private DataBasePrepareBusiness dataBasePrepareService;
  @Autowired
  private PersonDao personDao;
  
  private static PersonEntity mickPersonEntity;
  private static PersonEntity youngPersonEntity;

  @BeforeEach
  private void setUpPerTest() throws Exception {
    dataBasePrepareService.clearDataBase();
    mickPersonEntity = MickBoydData.getPersonEntity();
    youngPersonEntity = YoungBoydData.getPersonEntity();
  }

  // -----------------------------------------------------------------------------------------------
  // Method getChildLivingInArea
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void getChildLivingInArea_Normal() {
    // GIVEN
    personDao.save(youngPersonEntity);
    // WHEN
    List<Person> result = childAlertBusiness.getChildLivingInArea("1234 Wall Street");
    // THEN
    assertThat(result.contains(YoungBoydData.getPerson())).isTrue();
    assertThat(result.size()).isEqualTo(1);
  }
  
  // Borderline cases : Empty list
  @Test
  void getChildLivingInArea_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(childAlertBusiness.getChildLivingInArea("1234 Wall Street")).isEmpty();
    // THEN
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method getOtherHouseholdPersons
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void getOtherHouseholdPersons_Normal() {
    // GIVEN
    personDao.save(mickPersonEntity);
    // WHEN
    List<Person> result = childAlertBusiness.getOtherHouseholdPersons("Young", "Boyd", "1234 Wall Street");
    // THEN
    assertThat(result.contains(MickBoydData.getPerson())).isTrue();
    assertThat(result.size()).isEqualTo(1);
  }
  
  // Borderline cases : Empty list
  @Test
  void getOtherHouseholdPersons_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(childAlertBusiness.getOtherHouseholdPersons("Young", "Boyd", "1234 Wall Street")).isEmpty();
    // THEN
  }
}
