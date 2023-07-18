package net.javaguides.springboot.repository.impl;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepositoryCustom;
import net.javaguides.springboot.utils.ValueUtils;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Employee> findAllCustom() {
        StringBuilder sb = new StringBuilder();
        sb.append("select e.ID, e.FIRST_NAME, e.LAST_NAME, e.EMAIL_ID " +
                "from EMPLOYEES e ");
        Query query = entityManager.createNativeQuery(sb.toString());
        List<Object[]> result = query.getResultList();
        List<Employee> response = new ArrayList<>();
        if (!StringUtils.isEmpty(result)) {
            for (Object[] obj : result) {
                Employee dto = new Employee();
                dto.setId(ValueUtils.getLongByObject(obj[0]));
                dto.setFirstName(ValueUtils.getStringByObject(obj[1]));
                dto.setLastName(ValueUtils.getStringByObject(obj[2]));
                dto.setEmailId(ValueUtils.getStringByObject(obj[3]));
                response.add(dto);
            }
        }
        return response;
    }

    @Override
    public Optional<Employee> findEmployeeByIdCustom(Long id) {
        StringBuilder sb = new StringBuilder();
        sb.append("select e.ID, e.FIRST_NAME, e.LAST_NAME, e.EMAIL_ID " +
                "from EMPLOYEES e where e.ID = :id ");
        Query query = entityManager.createNativeQuery(sb.toString());
        query.setParameter("id",id);
        List<Object[]> result = query.getResultList();
        Employee employee = new Employee();
        if (!StringUtils.isEmpty(result)) {
            for (Object[] obj : result) {
                employee.setId(ValueUtils.getLongByObject(obj[0]));
                employee.setFirstName(ValueUtils.getStringByObject(obj[1]));
                employee.setLastName(ValueUtils.getStringByObject(obj[2]));
                employee.setEmailId(ValueUtils.getStringByObject(obj[3]));
                return Optional.of(employee);
            }
        }
        return Optional.empty();
    }
    @Modifying
    @Transactional
    @Override
    public Employee updateEmployeeCustom(Employee employee, Long id) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE EMPLOYEES e SET e.FIRST_NAME = :firstName, e.LAST_NAME = :lastName, e.EMAIL_ID = :emailId " +
                "WHERE e.ID = :id ");
        Query query = entityManager.createNativeQuery(sb.toString());
        query.setParameter("firstName", employee.getFirstName());
        query.setParameter("lastName", employee.getLastName());
        query.setParameter("emailId", employee.getEmailId());
        query.setParameter("id", id);
        query.executeUpdate();
        return employee;
    }
    @Modifying
    @Transactional
    @Override
    public void deleteEmployeeCustom(Long id) {
        StringBuilder sb = new StringBuilder();
        sb.append(" DELETE EMPLOYEES e where e.ID = :id ");
        Query query = entityManager.createNativeQuery(sb.toString());
        query.setParameter("id",id);
        query.executeUpdate();
    }


}
