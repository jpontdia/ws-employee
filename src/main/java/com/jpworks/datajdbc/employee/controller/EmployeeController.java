package com.jpworks.datajdbc.employee.controller;

import com.jpworks.datajdbc.employee.vo.Employee;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Operation(summary = "Get the employee data")
    @GetMapping("/employee/{employeeNumber}")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Employee data"),
                            @ApiResponse(responseCode = "404", description = "No employee found")})
    public Employee employee(
            @Parameter(description  = "Employee number", required = true)
            @PathVariable("employeeNumber") long employeeNumber ) {
        log.debug("Employee id: {}", employeeNumber);
        return employeeRepository.findById(employeeNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No employee found"));
    }
}
