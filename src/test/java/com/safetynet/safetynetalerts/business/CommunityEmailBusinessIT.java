package com.safetynet.safetynetalerts.business;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.safetynet.safetynetalerts.dao.DataBasePrepareBusiness;
import com.safetynet.safetynetalerts.dao.db.PersonDao;
import com.safetynet.safetynetalerts.dao.db.entities.PersonEntity;
import com.safetynet.safetynetalerts.data.MickBoydData;
import com.safetynet.safetynetalerts.model.Person;

/**
 * CommunityEmailBusinessIT is a class of integration tests on email addresses of all the inhabitants of the city.
 * 
 * @author MC
 * @version 1.0
 */
@SpringBootTest
class CommunityEmailBusinessIT {
  
  @Autowired
  private CommunityEmailBusiness communityEmailBusiness;
  @Autowired
  private DataBasePrepareBusiness dataBasePrepareService;
  @Autowired
  private PersonDao personDao;

  private static PersonEntity mickPersonEntity;
  
  @BeforeEach
  private void setUpPerTest() throws Exception {
    dataBasePrepareService.clearDataBase();
    mickPersonEntity = MickBoydData.getPersonEntity();
  }

  // -----------------------------------------------------------------------------------------------
  // Method getPersonByCity
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, Get email addresses of all the inhabitants of the city
   */
  @Test
  void getPersonByCity_Normal() {
    // GIVEN
    personDao.save(mickPersonEntity);
    // WHEN
    List<Person> result = communityEmailBusiness.getPersonByCity("Culver");
    // THEN
    assertThat(result.contains(MickBoydData.getPerson())).isTrue();
    assertThat(result.size()).isEqualTo(1);
  }
  
  /**
   * Borderline case test, empty list, Get email addresses of all the inhabitants of the city
   */
  @Test
  void getPersonByCity_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(communityEmailBusiness.getPersonByCity("Culver")).isEmpty();
    // THEN
  }
}
