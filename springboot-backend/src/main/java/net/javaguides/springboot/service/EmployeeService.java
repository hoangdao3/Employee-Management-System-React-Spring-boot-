package net.javaguides.springboot.service;


import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;



    public List<Employee> findAllEmployeeCustom() {
        return employeeRepository.findAllCustom();
    }

    public Employee saveEmployeeCustom(Employee employee) {
        Employee employee1 = new Employee();
        employee1.setFirstName(employee.getFirstName());
        employee1.setLastName(employee.getLastName());
        employee1.setEmailId(employee.getEmailId());
        return employeeRepository.save(employee1);
    }


    public Optional<Employee> findEmployeeById (Long id) {
        return employeeRepository.findEmployeeByIdCustom(id);
    }

    public Employee updateEmployeeCustom(Employee employee, Long id) {
        return employeeRepository.updateEmployeeCustom(employee,id);
    }

    public void deleteEmployeeCustom(Long id) {
        employeeRepository.deleteEmployeeCustom(id);
    }
}
