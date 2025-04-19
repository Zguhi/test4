package com.example.baitap.Service.Imlp;

import com.example.baitap.Entity.Employee;
import com.example.baitap.Exception.NotFoundException;
import com.example.baitap.Repository.EmployeeRepository;
import com.example.baitap.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeServiceImlp implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public List<Employee> findAll() {
        List<Employee> list = employeeRepository.findAll(Sort.by("id").descending());
        return list;
    }

    @Override
    public Employee getAcademicYearsById(long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee create(Employee employee) {
        Employee employee1 = new Employee();
        employee1.setName(employee.getName());
        employee1.setBirthday(employee.getBirthday());
        employee1.setPhoneNumber(employee.getPhoneNumber());
        employee1.setEmail(employee.getEmail());
        return employeeRepository.save(employee1);
    }

    @Override
    public Employee update( Employee employee) {
        if (employee.getId() != null) {
            // Kiểm tra xem nhân viên có tồn tại trong cơ sở dữ liệu hay không
            Employee existingEmployee = employeeRepository.findById(employee.getId()).orElse(null);
            if (existingEmployee != null) {
                // Cập nhật các trường của nhân viên
                existingEmployee.setName(employee.getName());
                existingEmployee.setBirthday(employee.getBirthday());
                // Tiếp tục cập nhật các thuộc tính khác...
                employeeRepository.save(existingEmployee);  // Lưu bản ghi đã cập nhật
            }
        }
        return employeeRepository.save(employee);
    }

    @Override
    public void delete(Long id) {
        Employee employee1 = employeeRepository.findById(id).orElseThrow(() -> new NotFoundException("k tim thay id"));
        employeeRepository.delete(employee1);
    }
}
