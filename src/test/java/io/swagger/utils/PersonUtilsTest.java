package io.swagger.utils;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import io.swagger.dao.db.entities.PersonEntity;
import io.swagger.model.Person;

@SpringBootTest
public class PersonUtilsTest {
  
  private static PersonUtils personUtils;
  private static List<PersonEntity> PersonEntities;
  private static List<Person> persons;

  @BeforeEach
  private void setUpPerTest() {
    personUtils = new PersonUtils();
    PersonEntities = new ArrayList<PersonEntity>();
    persons = new ArrayList<Person>();
  }

  // -----------------------------------------------------------------------------------------------
  // Method conversionListPersonEntityToPerson
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("")
  public void conversionListPersonEntityToPerson_nullPersonEntities_returnNull() {
    // GIVEN
    // WHEN
    List<Person> result = personUtils.conversionListPersonEntityToPerson(PersonEntities);
    // THEN
    assertThat(result.equals(persons));
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
    PersonEntities.add(personEntity);
    
    Person person = new Person();
    person.setId(personEntity.getId());
    person.setFirstName(personEntity.getFirstName());
    person.setLastName(personEntity.getLastName());
    person.setAge(personEntity.getAge());
    persons.add(person);
    // WHEN
    List<Person> result = personUtils.conversionListPersonEntityToPerson(PersonEntities);
    // THEN
    assertThat(result.equals(persons));
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
    Person result = personUtils.conversionPersonEntityToPerson(personEntity);
    // THEN
    assertThat(result.equals(person));
  }
}
