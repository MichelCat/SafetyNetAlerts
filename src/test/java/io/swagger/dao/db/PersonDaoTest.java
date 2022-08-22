package io.swagger.dao.db;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import io.swagger.dao.db.entities.FireStationEntity;
import io.swagger.dao.db.entities.PersonEntity;
import io.swagger.model.Person;

@SpringBootTest
public class PersonDaoTest {

  private static PersonDao personDao;
  private static SortedSet<FireStationEntity> fireStationEntities;
  private static SortedSet<PersonEntity> personEntities;

  private static List<PersonEntity> PersonEntityList;
  private static List<Person> personList;

  @MockBean
  private SafetyNetDataBase safetyNetDataBase;

  @BeforeEach
  private void setUpPerTest() {
    personDao = new PersonDao();
    fireStationEntities = new TreeSet<FireStationEntity>();
    personEntities = new TreeSet<PersonEntity>();

    PersonEntityList = new ArrayList<PersonEntity>();
    personList = new ArrayList<Person>();
  }

  // -----------------------------------------------------------------------------------------------
  // Method conversionListPersonEntityToPerson
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("")
  public void conversionListPersonEntityToPerson_nullPersonEntities_returnNull() {
    // GIVEN
    // WHEN
    List<Person> result = personDao.conversionListPersonEntityToPerson(PersonEntityList);
    // THEN
    assertThat(result.equals(personList));
  }
  
  @Test
  @DisplayName("")
  public void conversionListPersonEntityToPerson_PersonEntity_returnPersons() {
    // GIVEN
    PersonEntity personEntity = new PersonEntity();
    personEntity.setId(1);
    personEntity.setFirstName("John");
    personEntity.setLastName("lastName");
    personEntity.setBirthdate(new Date("2002/06/05"));
    PersonEntityList.add(personEntity);
    
    Person person = new Person();
    person.setId(personEntity.getId());
    person.setFirstName(personEntity.getFirstName());
    person.setLastName(personEntity.getLastName());
    person.setAge(personEntity.getAge());
    personList.add(person);
    // WHEN
    List<Person> result = personDao.conversionListPersonEntityToPerson(PersonEntityList);
    // THEN
    assertThat(result.equals(personList));
  }

  // -----------------------------------------------------------------------------------------------
  // Method conversionPersonEntityToPerson
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("")
  public void conversionPersonEntityToPerson_personEntity_returnPerson() {
    // GIVEN
    PersonEntity personEntity = new PersonEntity();
    personEntity.setId(1);
    personEntity.setFirstName("John");
    personEntity.setLastName("lastName");
    personEntity.setBirthdate(new Date("2002/06/05"));
    
    Person person = new Person();
    person.setId(personEntity.getId());
    person.setFirstName(personEntity.getFirstName());
    person.setLastName(personEntity.getLastName());
    person.setAge(personEntity.getAge());
    // WHEN
    Person result = personDao.conversionPersonEntityToPerson(personEntity);
    // THEN
    assertThat(result.equals(person));
  }

  // -----------------------------------------------------------------------------------------------
  // Method findPersonByStationNumber
  // -----------------------------------------------------------------------------------------------
//   @Test
//   @DisplayName("")
//   public void findPersonByStationNumber() {
//   // GIVEN
//   when(safetyNetDataBase.getFireStationEntities()).thenReturn(fireStationEntities);
//   when(safetyNetDataBase.getPersonEntities()).thenReturn(personEntities);
//   // WHEN
//   assertThat(personDao.findPersonByStationNumber(3).size() == 0);
//   // THEN
//   }

}
