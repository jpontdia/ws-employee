package com.jpworks.datajdbc.employee.controller;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
public class Salaries {
    @Id
    private LocalDate fromDate;
    private long salary;
    private LocalDate toDate;
}
