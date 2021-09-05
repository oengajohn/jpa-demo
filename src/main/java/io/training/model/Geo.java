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
public class Geo {

  @Column(name = "lng")
  private String lng;

  @Column(name = "lat")
  private String lat;

}
