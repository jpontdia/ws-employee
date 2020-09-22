package com.jpworks.datajdbc.employee.vo;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Salary {
    private LocalDate fromDate;
    private long salary;
    private LocalDate toDate;
}
