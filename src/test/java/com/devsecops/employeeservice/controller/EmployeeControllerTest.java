package com.devsecops.employeeservice.controller;

import com.devsecops.employeeservice.entity.Employee;
import com.devsecops.employeeservice.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService service;

    private Employee buildEmployee() {
        return new Employee(
                1L,
                "Karan",
                "karan@test.com",
                "IT",
                50000.0
        );
    }

    private Employee buildUpdatedEmployee() {
        return new Employee(
                1L,
                "Updated",
                "updated@test.com",
                "DevOps",
                70000.0
        );
    }

    private String employeeJson() {
        return """
                {
                  "name":"Karan",
                  "email":"karan@test.com",
                  "department":"IT",
                  "salary":50000
                }
                """;
    }

    private String updatedEmployeeJson() {
        return """
                {
                  "name":"Updated",
                  "email":"updated@test.com",
                  "department":"DevOps",
                  "salary":70000
                }
                """;
    }

    @Test
    void getAllEmployees() throws Exception {

        when(service.getAllEmployees())
                .thenReturn(List.of(buildEmployee()));

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Karan"));
    }

    @Test
    void getEmployeeById() throws Exception {

        when(service.getEmployeeById(1L))
                .thenReturn(buildEmployee());

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("karan@test.com"));
    }

    @Test
    void createEmployee() throws Exception {

        when(service.saveEmployee(any(Employee.class)))
                .thenReturn(buildEmployee());

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employeeJson()))
                .andExpect(status().isCreated());
    }

    @Test
    void updateEmployee() throws Exception {

        when(service.updateEmployee(eq(1L), any(Employee.class)))
                .thenReturn(buildUpdatedEmployee());

        mockMvc.perform(put("/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedEmployeeJson()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated"));
    }

    @Test
    void deleteEmployee() throws Exception {

        doNothing().when(service).deleteEmployee(1L);

        mockMvc.perform(delete("/employees/1"))
                .andExpect(status().isNoContent());
    }
}
