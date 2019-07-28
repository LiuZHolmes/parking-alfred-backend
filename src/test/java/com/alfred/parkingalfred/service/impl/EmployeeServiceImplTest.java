package com.alfred.parkingalfred.service.impl;

import com.alfred.parkingalfred.entity.Employee;
import com.alfred.parkingalfred.repository.EmployeeRepository;
import com.alfred.parkingalfred.service.EmployeeService;
import com.alfred.parkingalfred.utils.EncodingUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmployeeServiceImplTest {

    private EmployeeRepository employeeRepository;

    private EmployeeService employeeService;

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        employeeRepository = mock(EmployeeRepository.class);
        employeeService = new EmployeeServiceImpl(employeeRepository);
        objectMapper = new ObjectMapper();
    }

    @Test
    public void should_return_employee_when_get_employee_by_name_and_password() throws JsonProcessingException {
        String name = "name";
        String password = "password";
        String encodedPassword = EncodingUtil.encodingByMd5(password);

        Employee employee = new Employee();
        when(employeeRepository.findByNameAndPassword(name, encodedPassword)).thenReturn(employee);
        Employee actualEmployee = employeeService.getEmployeeByNameAndPassword(name, password);

        assertEquals(objectMapper.writeValueAsString(employee), objectMapper.writeValueAsString(actualEmployee));
    }
}