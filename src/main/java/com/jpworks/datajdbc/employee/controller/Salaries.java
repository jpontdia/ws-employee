package com.jpworks.datajdbc.employee.controller;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Salaries {
    private LocalDate fromDate;
    private long salary;
    private LocalDate toDate;
}
