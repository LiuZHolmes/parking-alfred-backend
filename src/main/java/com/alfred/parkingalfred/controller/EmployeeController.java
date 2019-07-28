package com.alfred.parkingalfred.controller;

import com.alfred.parkingalfred.entity.Employee;
import com.alfred.parkingalfred.entity.ParkingLot;
import com.alfred.parkingalfred.enums.ResultEnum;
import com.alfred.parkingalfred.service.EmployeeService;
import com.alfred.parkingalfred.service.ParkingLotService;
import com.alfred.parkingalfred.utils.JwtUtil;
import com.alfred.parkingalfred.vo.ResultVO;
import com.alfred.parkingalfred.utils.ResultVOUtil;
import java.util.List;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

  private final EmployeeService employeeService;

  private final ParkingLotService parkingLotService;

  public EmployeeController(EmployeeService employeeService, ParkingLotService parkingLotService) {
    this.employeeService = employeeService;
    this.parkingLotService = parkingLotService;
  }

  @GetMapping(value = "/login", params = {"name", "password"})
  public ResultVO<Object> login(@RequestParam String name, @RequestParam String password) {
    Employee employee = employeeService.getEmployeeByNameAndPassword(name, password);
    String token = JwtUtil.generateToken(employee);
    return ResultVOUtil.success(token);
  }

  @GetMapping(value = "/employee/{employeeId}/parking-lots")
  public ResultVO<List<ParkingLot>> getStatusOfEmployeeById(@PathVariable Long employeeId) {
    List<ParkingLot> parkingLots = parkingLotService.getParkingLotsByParkingBoyId(employeeId);
    return  ResultVOUtil.success(parkingLots);
  }
  @GetMapping(value = "/employee/{employeeId}/status")
  public ResultVO getEmployeeParkingLotStatus(@PathVariable Long employeeId){
    boolean result = employeeService.doesEmplyeeHasNotFullParkingLots(employeeId);
    return  ResultVOUtil.success(result);
  }

}
