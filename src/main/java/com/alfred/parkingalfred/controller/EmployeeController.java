package com.alfred.parkingalfred.controller;

import com.alfred.parkingalfred.entity.Employee;
import com.alfred.parkingalfred.service.EmployeeService;
import com.alfred.parkingalfred.utils.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/login", params = {"name", "password"})
    public ResponseEntity login(@RequestParam String name, @RequestParam String password) {
        Employee employee = employeeService.getEmployeeByNameAndPassword(name, password);
        if (employee == null)
            return ResponseEntity.notFound().build();
        String token = JwtUtil.generateToken(employee);
        return ResponseEntity.ok(token);
    }
}
