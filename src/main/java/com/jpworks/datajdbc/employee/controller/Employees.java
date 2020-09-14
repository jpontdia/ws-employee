package com.jpworks.datajdbc.employee.controller;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
public class Employees {
    @Id
    private long employeeNumber;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private LocalDate hireDate;
    private String gender;
}
