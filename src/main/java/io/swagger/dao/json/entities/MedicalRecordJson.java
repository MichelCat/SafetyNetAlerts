package io.swagger.dao.json.entities;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

// { "firstName":"John", "lastName":"Boyd", "birthdate":"03/06/1984", "medications":["aznol:350mg", "hydrapermazol:100mg"], "allergies":["nillacilan"] }
public class MedicalRecordJson {

  @Getter @Setter
  private String firstName;
  
  @Getter @Setter
  private String lastName;
  
  @Getter @Setter
  private String birthdate;
  
  @Getter @Setter
  private List<String> medications;
  
  @Getter @Setter
  private List<String> allergies;
}
