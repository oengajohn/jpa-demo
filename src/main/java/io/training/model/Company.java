package io.training.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter
@Setter
@ToString
public class Company {

  @Column(name = "bs")
  private String bs;

  @Column(name = "company_name")
  private String name;

  @Column(name = "catch_phrase")
  private String catchPhrase;

}
