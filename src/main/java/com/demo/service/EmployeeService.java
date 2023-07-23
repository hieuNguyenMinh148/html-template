package com.demo.service;

import com.demo.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getAll();
    void save(Employee employee);
    void delete(int id);
    Optional<Employee> findByID(int id);
}
