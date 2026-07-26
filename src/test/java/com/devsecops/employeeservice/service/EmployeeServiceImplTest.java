package com.devsecops.employeeservice.service;

import com.devsecops.employeeservice.entity.Employee;
import com.devsecops.employeeservice.exception.ResourceNotFoundException;
import com.devsecops.employeeservice.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeServiceImpl service;

    private Employee employee;

    @BeforeEach
    void setup() {
        employee = new Employee();
        employee.setId(1L);
        employee.setName("Karan");
        employee.setEmail("karan@test.com");
        employee.setDepartment("IT");
        employee.setSalary(50000.0);
    }

    @Test
    void getAllEmployeesTest() {

        when(repository.findAll()).thenReturn(Arrays.asList(employee));

        assertEquals(1, service.getAllEmployees().size());

        verify(repository, times(1)).findAll();
    }

    @Test
    void getEmployeeByIdTest() {

        when(repository.findById(1L)).thenReturn(Optional.of(employee));

        Employee result = service.getEmployeeById(1L);

        assertNotNull(result);
        assertEquals("Karan", result.getName());
        assertEquals("karan@test.com", result.getEmail());

        verify(repository).findById(1L);
    }

    @Test
    void employeeNotFoundTest() {

        when(repository.findById(100L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> service.getEmployeeById(100L));

        verify(repository).findById(100L);
    }

    @Test
    void saveEmployeeTest() {

        when(repository.save(employee)).thenReturn(employee);

        Employee result = service.saveEmployee(employee);

        assertNotNull(result);
        assertEquals(employee, result);

        verify(repository).save(employee);
    }

    @Test
    void updateEmployeeTest() {

        Employee updated = new Employee();
        updated.setName("Updated");
        updated.setEmail("updated@test.com");
        updated.setDepartment("DevOps");
        updated.setSalary(60000.0);

        when(repository.findById(1L)).thenReturn(Optional.of(employee));
        when(repository.save(any(Employee.class))).thenReturn(employee);

        Employee result = service.updateEmployee(1L, updated);

        assertNotNull(result);

        verify(repository).findById(1L);
        verify(repository).save(any(Employee.class));
    }

    @Test
    void deleteEmployeeTest() {

        doNothing().when(repository).deleteById(1L);

        service.deleteEmployee(1L);

        verify(repository).deleteById(1L);
    }
}
