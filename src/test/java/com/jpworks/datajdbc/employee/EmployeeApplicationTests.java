package com.jpworks.datajdbc.employee;

import com.jpworks.datajdbc.employee.controller.EmployeeRepository;
import com.jpworks.datajdbc.employee.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class EmployeeApplicationTests {

	@Autowired
	EmployeeRepository employeeRepository;

	@Test
	void createDeleteEmployee() {

		long currentRecords = employeeRepository.count();
		log.info("Current number of employee records: {}", currentRecords);

		Address address1 = Address.builder()
				.address("405 Wisconsin Ave Lynn Haven, Florida(FL), 32444")
				.fromDate(LocalDate.of(2020, 2, 1))
				.toDate(LocalDate.of(2020, 4, 30))
				.build();

		Address address2 = Address.builder()
				.address("6001 Farm To Market Rd, Cattaraugus, New York(NY), 14719")
				.fromDate(LocalDate.of(2020, 5, 1))
				.toDate(null)
				.build();
		var addressMap = new HashMap<LocalDate, Address>();
		addressMap.put(address1.getFromDate(), address1);
		addressMap.put(address2.getFromDate(), address2);

		Salary salary1 = Salary.builder()
				.fromDate(LocalDate.of(2020, 1, 1))
				.toDate(LocalDate.of(2020, 4, 30))
				.salary(150000)
				.build();

		Salary salary2 = Salary.builder()
				.fromDate(LocalDate.of(2020, 5, 1))
				.salary(160000)
				.build();

		var salaryMap = new HashMap<LocalDate, Salary>();
		salaryMap.put(salary1.getFromDate(), salary1);
		salaryMap.put(salary2.getFromDate(), salary2);


		Employee employee = Employee.builder()
				.firstName("Arjun")
				.lastName("Mahoney")
				.birthDate(LocalDate.of(1996,9,1))
				.hireDate(LocalDate.of(1970,1,1))
				.gender(Gender.M)
				.addresses(addressMap)
				.salaries(salaryMap)
				.status(EmployeeStatus.A)
				.build();

		//Create
		employeeRepository.save(employee);
		long newRecords = employeeRepository.count();
		assertThat(newRecords).isEqualTo(currentRecords + 1);
		log.info("Current number of employee records: {}", newRecords);
		Long currentId = employee.getId();
		log.info("Id for new record: {}", currentId);
		assertThat(currentId).isNotNull();

		//Update
		employee.setPhone("2222");
		employeeRepository.save(employee);
		log.info("Id for the updated record: {}", employee.getId());
		assertThat(employee.getId()).isEqualTo(currentId);

		//Delete
		employeeRepository.delete(employee);
		long recordsAfterDelete = employeeRepository.count();
		assertThat(recordsAfterDelete).isEqualTo(currentRecords);
		log.info("Current number of employee records: {}", recordsAfterDelete);
	}

}
