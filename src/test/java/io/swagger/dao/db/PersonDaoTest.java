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

  @BeforeEach
  private void setUpPerTest() {
    personDao = new PersonDao();
    fireStationEntities = new TreeSet<FireStationEntity>();
    personEntities = new TreeSet<PersonEntity>();
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
