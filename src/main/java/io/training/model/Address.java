package io.training.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Embeddable
@ToString
public class Address {

  @Embedded
  private Geo geo;

  @Column(name = "city")
  private String city;

  @Column(name = "zipcode")
  private String zipcode;

  @Column(name = "suite")
  private String suite;

  @Column(name = "street")
  private String street;


}
