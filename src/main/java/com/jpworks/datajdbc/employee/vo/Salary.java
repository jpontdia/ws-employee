package com.jpworks.datajdbc.employee.vo;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class Salary {
    private LocalDate fromDate;
    private long salary;
    private LocalDate toDate;
}
