package com.devsecops.employeeservice.controller;

import com.devsecops.employeeservice.dto.EmployeeRequest;
import com.devsecops.employeeservice.entity.Employee;
import com.devsecops.employeeservice.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return service.getEmployeeById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@Valid @RequestBody EmployeeRequest request) {
        return service.saveEmployee(mapToEmployee(request));
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id,
                                   @Valid @RequestBody EmployeeRequest request) {
        return service.updateEmployee(id, mapToEmployee(request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
    }

    private Employee mapToEmployee(EmployeeRequest request) {
        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setDepartment(request.getDepartment());
        employee.setSalary(request.getSalary());
        return employee;
    }
}
