package com.alfred.parkingalfred.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ParkingLot {

  @Id
  @GeneratedValue
  private Long id;

  @Column(unique = true, nullable = false)
  private String name;

  private Integer capacity;

  private Integer occupied;

  @ManyToMany(mappedBy = "parkingLots")
  private List<Employee> employees;
}
