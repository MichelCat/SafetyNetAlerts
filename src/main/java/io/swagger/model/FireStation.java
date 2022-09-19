package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import lombok.Getter;
import lombok.Setter;

/**
 * FireStation
 */
@Validated
public class FireStation   {
  @JsonProperty("id")
  @Getter @Setter
  private Integer id = null;

  @JsonProperty("address")
  @Getter @Setter
  private String address = null;

  public FireStation id(Integer id) {
    this.id = id;
    return this;
  }

  public FireStation address(String address) {
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
    FireStation fireStation = (FireStation) o;
    return Objects.equals(this.id, fireStation.id) &&
        Objects.equals(this.address, fireStation.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, address);
  }

  @Override
  public String toString() {
    return "FireStation [id=" + id + ", address=" + address + "]";
  }
}
