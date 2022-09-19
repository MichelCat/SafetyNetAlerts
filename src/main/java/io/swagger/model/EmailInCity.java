package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import lombok.Getter;
import lombok.Setter;

/**
 * EmailInCity
 */
@Validated
public class EmailInCity   {
  @JsonProperty("email")
  @Getter @Setter
  private String email = null;

  public EmailInCity email(String email) {
    this.email = email;
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
    EmailInCity emailInCity = (EmailInCity) o;
    return Objects.equals(this.email, emailInCity.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email);
  }

  @Override
  public String toString() {
    return "EmailInCity [email=" + email + "]";
  }
}
