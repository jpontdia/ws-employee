package com.jpworks.datajdbc.employee.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class EmployeesController {

    @Autowired
    EmployeesRepository employeesRepository;

    @Operation(summary = "Get the employee data")
    @GetMapping("/employee/{employeeNumber}")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Employee data"),
                            @ApiResponse(responseCode = "404", description = "No employee found")})
    public Employees employee(
            @Parameter(description  = "Employee number", required = true)
            @PathVariable("employeeNumber") long employeeNumber ) {
        return employeesRepository.findById(employeeNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No employee found"));
    }
}
