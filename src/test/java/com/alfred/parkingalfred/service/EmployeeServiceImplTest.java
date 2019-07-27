package com.alfred.parkingalfred.service;

import com.alfred.parkingalfred.entity.Employee;
import com.alfred.parkingalfred.repository.EmployeeRepository;
import com.alfred.parkingalfred.utils.EncodingUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceImplTest {

    @MockBean
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void should_return_employee_when_get_employee_by_name_and_password() throws JsonProcessingException {
        String name = "name";
        String password = "password";
        String encodedPassword = EncodingUtil.encodingByMd5(password);

        Employee employee = new Employee();
        given(employeeRepository.findByNameAndPassword(name, encodedPassword)).willReturn(employee);
        Employee actualEmployee = employeeService.getEmployeeByNameAndPassword(name, password);

        assertEquals(objectMapper.writeValueAsString(employee), objectMapper.writeValueAsString(actualEmployee));
    }
}