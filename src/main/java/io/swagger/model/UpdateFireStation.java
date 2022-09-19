package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import lombok.Getter;
import lombok.Setter;

/**
 * UpdateFireStation
 */
@Validated
public class UpdateFireStation   {
  @JsonProperty("oldStation")
  @Getter @Setter
  private Integer oldStation = null;

  @JsonProperty("newStation")
  @Getter @Setter
  private Integer newStation = null;

  @JsonProperty("address")
  @Getter @Setter
  private String address = null;

  public UpdateFireStation oldStation(Integer oldStation) {
    this.oldStation = oldStation;
    return this;
  }

  public UpdateFireStation newStation(Integer newStation) {
    this.newStation = newStation;
    return this;
  }

  public UpdateFireStation address(String address) {
    this.address = address;
    return this;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateFireStation updateFireStation = (UpdateFireStation) o;
    return Objects.equals(this.oldStation, updateFireStation.oldStation) &&
        Objects.equals(this.newStation, updateFireStation.newStation) &&
        Objects.equals(this.address, updateFireStation.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(oldStation, newStation, address);
  }

  @Override
  public String toString() {
    return "UpdateFireStation [oldStation=" + oldStation + ", newStation=" + newStation + ", address=" + address + "]";
  }
}
