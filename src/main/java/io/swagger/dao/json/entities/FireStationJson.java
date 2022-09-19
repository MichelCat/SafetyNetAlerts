package io.swagger.dao.json.entities;

import lombok.Getter;
import lombok.Setter;

// { "address":"1509 Culver St", "station":"3" }
public class FireStationJson {

  @Getter @Setter
  private String address;
  
  @Getter @Setter
  private String station;
}
