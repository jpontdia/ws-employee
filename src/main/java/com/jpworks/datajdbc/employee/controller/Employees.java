package com.jpworks.datajdbc.employee.controller;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Data
public class Employees {
    @Id
    private long employeeNumber;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private LocalDate hireDate;
    private String gender;

    @MappedCollection(idColumn = "employee_number", keyColumn = "from_date")
    Map<LocalDate, Salaries> salaries = new HashMap<>();

    @MappedCollection(idColumn = "employee_number", keyColumn = "from_date")
    Map<LocalDate, Addresses> addresses = new HashMap<>();
}
