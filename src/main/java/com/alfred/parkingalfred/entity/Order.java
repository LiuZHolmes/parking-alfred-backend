package com.alfred.parkingalfred.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "orders")
@Entity
@Data
@NoArgsConstructor
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private Long orderId;

  private Integer type;

  private Long reservationTime;

  private String customerAddress;

  private Integer status;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "employeeId",referencedColumnName="id")
  private Employee employee;

  @OneToOne
  @JoinColumn(name = "carId",referencedColumnName="id")
  private Car car;

  @OneToOne
  @JoinColumn(name = "parkingLotId",referencedColumnName="id")
  private ParkingLot parkingLot;

  public Order(Long orderId, Integer type,  String customerAddress, Integer status) {
    this.orderId = orderId;
    this.type = type;
    this.customerAddress = customerAddress;
    this.status = status;
  }

  public Order(Long orderId, Integer type, Long reservationTime, String customerAddress, Integer status, Employee employee, Car car, ParkingLot parkingLot) {
    this.orderId = orderId;
    this.type = type;
    this.reservationTime = reservationTime;
    this.customerAddress = customerAddress;
    this.status = status;
    this.employee = employee;
    this.car = car;
    this.parkingLot = parkingLot;
  }
}
