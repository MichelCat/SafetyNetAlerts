package io.swagger.dao.json.entities;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class SafetyNetJson {
  
  @Getter @Setter
  private List<PersonJson> persons;
  
  @Getter @Setter
  private List<FireStationJson> firestations;
  
  @Getter @Setter
  private List<MedicalRecordJson> medicalrecords;
}
