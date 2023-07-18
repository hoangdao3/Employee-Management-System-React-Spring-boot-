package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepositoryCustom {

    List<Employee> findAllCustom();

    Optional<Employee> findEmployeeByIdCustom(Long id);

    Employee updateEmployeeCustom(Employee employee, Long id);

    void deleteEmployeeCustom(Long id);
}
