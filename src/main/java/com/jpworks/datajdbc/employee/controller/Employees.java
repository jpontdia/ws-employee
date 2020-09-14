package com.jpworks.datajdbc.employee.controller;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class Employees {
    @Id
    private long employeeNumber;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private LocalDate hireDate;
    private String gender;

    @MappedCollection(idColumn = "employee_number")
    Set<Salaries> salaries = new HashSet<>();
}
