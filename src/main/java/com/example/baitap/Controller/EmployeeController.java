package com.example.baitap.Controller;

import com.example.baitap.Entity.Employee;

import com.example.baitap.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")  // Đổi đường dẫn thành /employees
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String getEmployees(Model model) {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "employeeList"; // Tên file HTML (employeeList.html)
    }
    @GetMapping("/employees")
    public String showEmployeeList(Model model) {
        Employee employee = new Employee();
        // Thiết lập các thuộc tính cho đối tượng employee nếu cần
        model.addAttribute("employee", employee);
        return "employeeList"; // Tên template Thymeleaf của bạn
    }

    @GetMapping("/add")
    public String showAddEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employeeList";
    }

    @PostMapping("/add") // Đảm bảo đường dẫn khớp
    public String addEmployee(@ModelAttribute Employee employee) {
        employeeService.create(employee);
        return "redirect:/employees"; // Sử dụng "/employees" thay vì "/employee"
    }


    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
        return "redirect:/employees"; // Sử dụng "/employees"
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        // Lấy dữ liệu nhân viên theo id
        Employee employee = employeeService.getAcademicYearsById(id);
        model.addAttribute("employee", employee);
        return "editEmployee";  // trả về trang chỉnh sửa nhân viên
    }

    @PostMapping("/edit")
    public String updateEmployee(@ModelAttribute  Employee employee) {
        // Cập nhật thông tin nhân viên
        employeeService.update(employee);
        return "redirect:/employees";  // chuyển hướng về danh sách nhân viên sau khi cập nhật
    }




}
