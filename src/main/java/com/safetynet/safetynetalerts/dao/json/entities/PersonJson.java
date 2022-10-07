package com.safetynet.safetynetalerts.dao.json.entities;

import lombok.Getter;
import lombok.Setter;

/**
 * PersonJson is JSON model
 * 
 * @author MC
 * @version 1.0
 */
public class PersonJson {
  // { "firstName":"John", "lastName":"Boyd", "address":"1509 Culver St", "city":"Culver", "zip":"97451", "phone":"841-874-6512", "email":"jaboyd@email.com" }

  @Getter
  @Setter
  private String firstName;

  @Getter
  @Setter
  private String lastName;

  @Getter
  @Setter
  private String address;

  @Getter
  @Setter
  private String city;

  @Getter
  @Setter
  private String zip;

  @Getter
  @Setter
  private String phone;

  @Getter
  @Setter
  private String email;
}
