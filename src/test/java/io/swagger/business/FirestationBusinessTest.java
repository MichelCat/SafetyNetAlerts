package io.swagger.business;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import io.swagger.dao.db.PersonDao;
import io.swagger.dao.db.entities.PersonEntity;
import io.swagger.model.Person;

@SpringBootTest
public class FirestationBusinessTest {
  private static FirestationBusiness personBusiness;

  private static List<PersonEntity> personEntities;
  private static List<Person> persons;
  
  @MockBean
  private PersonDao personDao;
  
  @BeforeEach
  private void setUpPerTest() {
    personEntities = new ArrayList<PersonEntity>();
    persons = new ArrayList<Person>();
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method getPersonsLivingNearStation
  // -----------------------------------------------------------------------------------------------
//   @Test
//   @DisplayName("")
//   public void getPersonsLivingNearStation() {
//   // GIVEN
//   when(personDao.findPersonByStationNumber(any(Integer.class))).thenReturn(personEntities);
//   when(personDao.conversionListPersonEntityToPerson((List<PersonEntity>) any(Object.class))).thenReturn(persons);
//   // WHEN
//   assertThat(personBusiness.getPersonsLivingNearStation("3").size() == 0);
//   // THEN
//   }

}
