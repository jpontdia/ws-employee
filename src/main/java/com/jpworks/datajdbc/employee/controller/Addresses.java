package com.jpworks.datajdbc.employee.controller;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Addresses {
    private LocalDate fromDate;
    private LocalDate toDate;
    private String address;
}
