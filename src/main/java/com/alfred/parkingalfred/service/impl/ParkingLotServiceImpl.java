package com.alfred.parkingalfred.service.impl;

import com.alfred.parkingalfred.entity.Employee;
import com.alfred.parkingalfred.entity.ParkingLot;
import com.alfred.parkingalfred.enums.ResultEnum;
import com.alfred.parkingalfred.exception.EmployeeNotExistedException;
import com.alfred.parkingalfred.repository.EmployeeRepository;
import com.alfred.parkingalfred.repository.ParkingLotRepository;
import com.alfred.parkingalfred.service.ParkingLotService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {

  private final ParkingLotRepository parkingLotRepository;

  private final EmployeeRepository employeeRepository;

  public ParkingLotServiceImpl(ParkingLotRepository parkingLotRepository, EmployeeRepository employeeRepository) {
    this.parkingLotRepository = parkingLotRepository;
    this.employeeRepository = employeeRepository;
  }

  @Override
  public List<ParkingLot> getParkingLotsByParkingBoyId(Long parkingBoyId) {
    Employee employee = employeeRepository.findById(parkingBoyId).orElseThrow(() ->
        new EmployeeNotExistedException(ResultEnum.RESOURCES_NOT_EXISTED));
    return employee.getParkingLots();
  }
}
