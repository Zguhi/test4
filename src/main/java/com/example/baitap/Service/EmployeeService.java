package com.example.baitap.Service;

import com.example.baitap.Entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee getAcademicYearsById(long id);
    Employee create(Employee employee);
    Employee update(Employee employee);
    void delete(Long id);
}
