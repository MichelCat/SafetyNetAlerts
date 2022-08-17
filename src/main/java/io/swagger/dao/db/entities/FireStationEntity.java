package io.swagger.dao.db.entities;

public class FireStationEntity implements Comparable<FireStationEntity> {
  private Integer station;
  private String address;
  
  public Integer getStation() {
    return station;
  }
  public void setStation(Integer station) {
    this.station = station;
  }
  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public int compareTo(FireStationEntity o) {
    return this.station.compareTo(o.station);
  }
}
