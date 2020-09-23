package com.jpworks.datajdbc.employee.controller;

import com.jpworks.datajdbc.employee.vo.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
