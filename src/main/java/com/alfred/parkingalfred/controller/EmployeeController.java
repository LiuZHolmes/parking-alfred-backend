package com.alfred.parkingalfred.controller;

import com.alfred.parkingalfred.entity.Employee;
import com.alfred.parkingalfred.entity.ParkingLot;
import com.alfred.parkingalfred.service.EmployeeService;
import com.alfred.parkingalfred.service.ParkingLotService;
import com.alfred.parkingalfred.utils.JwtUtil;
import com.alfred.parkingalfred.vo.ResultVO;
import com.alfred.parkingalfred.utils.ResultVOUtil;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

  private final EmployeeService employeeService;

  private final ParkingLotService parkingLotService;

  public EmployeeController(EmployeeService employeeService, ParkingLotService parkingLotService) {
    this.employeeService = employeeService;
    this.parkingLotService = parkingLotService;
  }

  @PostMapping(value = "/login")
  public ResultVO login(@RequestBody Employee loginEmployee) {
    Employee employee = employeeService.getEmployeeByNameAndPassword(loginEmployee.getName(), loginEmployee.getPassword());
    String token = JwtUtil.generateToken(employee);
    return ResultVOUtil.success(token);
  }

  @GetMapping(value = "/employee/{employeeId}/parking-lots")
  public ResultVO getParkingLotsByEmployeeId(@PathVariable Long employeeId) {
    List<ParkingLot> parkingLots = parkingLotService.getParkingLotsByParkingBoyId(employeeId);
    return  ResultVOUtil.success(parkingLots);
  }

}
