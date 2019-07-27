package com.alfred.parkingalfred.controller;

import com.alfred.parkingalfred.entity.Employee;
import com.alfred.parkingalfred.enums.RoleEnum;
import com.alfred.parkingalfred.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_return_ok_when_login_with_correct_account() throws Exception {
        String name = "name";
        String password = "password";

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setRole(RoleEnum.PARKING_BOY.getCode());
        given(employeeService.getEmployeeByNameAndPassword(anyString(), anyString())).willReturn(employee);

        mockMvc.perform(get("/login")
                .param("name", name)
                .param("password", password))
                .andExpect(status().isOk());
    }

    @Test
    public void should_return_404_when_login_with_invalid_account() throws Exception {
        String name = "name";
        String password = "password";

        given(employeeService.getEmployeeByNameAndPassword(anyString(), anyString())).willReturn(null);

        mockMvc.perform(get("/login")
                .param("name", name)
                .param("password", password))
                .andExpect(status().isNotFound());
    }
}