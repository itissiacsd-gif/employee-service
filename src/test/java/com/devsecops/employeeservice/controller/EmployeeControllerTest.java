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

    @Test
    void getAllEmployees() throws Exception {

        Employee employee =
                new Employee(1L,"Karan","karan@test.com","IT",50000.0);

        when(service.getAllEmployees())
                .thenReturn(List.of(employee));

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Karan"));
    }

    @Test
    void getEmployeeById() throws Exception {

        Employee employee =
                new Employee(1L,"Karan","karan@test.com","IT",50000.0);

        when(service.getEmployeeById(1L))
                .thenReturn(employee);

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email")
                        .value("karan@test.com"));
    }

    @Test
    void createEmployee() throws Exception {

        Employee employee =
                new Employee(1L,"Karan","karan@test.com","IT",50000.0);

        when(service.saveEmployee(any(Employee.class)))
                .thenReturn(employee);

        String json = """
                {
                  "name":"Karan",
                  "email":"karan@test.com",
                  "department":"IT",
                  "salary":50000
                }
                """;

        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    void updateEmployee() throws Exception {

        Employee employee =
                new Employee(1L,"Updated","updated@test.com","DevOps",70000.0);

        when(service.updateEmployee(eq(1L), any(Employee.class)))
                .thenReturn(employee);

        String json = """
                {
                  "name":"Updated",
                  "email":"updated@test.com",
                  "department":"DevOps",
                  "salary":70000
                }
                """;

        mockMvc.perform(put("/employees/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
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
