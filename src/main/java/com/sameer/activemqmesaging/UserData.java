package com.sameer.activemqmesaging;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserData implements Serializable {
  @JsonProperty
  private String name;
  @JsonProperty
  private int age;
  @JsonProperty
  private int rating;
}
