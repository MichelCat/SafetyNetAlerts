package io.swagger.dao.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MedicationDao {

  @Autowired
  SafetyNetDataBase safetyNetDataBase;

}
