package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import lombok.Getter;
import lombok.Setter;

/**
 * Error
 */
@Validated
public class Error   {
  @JsonProperty("code")
  @Getter @Setter
  private String code = null;

  @JsonProperty("message")
  @Getter @Setter
  private String message = null;

  public Error code(String code) {
    this.code = code;
    return this;
  }

  public Error message(String message) {
    this.message = message;
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
    Error error = (Error) o;
    return Objects.equals(this.code, error.code) &&
        Objects.equals(this.message, error.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message);
  }

  @Override
  public String toString() {
    return "Error [code=" + code + ", message=" + message + "]";
  }
}
