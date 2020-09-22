package com.jpworks.datajdbc.employee.vo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Address {
    private LocalDate fromDate;
    private LocalDate toDate;
    private String address;
}
