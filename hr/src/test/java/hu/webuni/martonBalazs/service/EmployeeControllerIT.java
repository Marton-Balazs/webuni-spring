package hu.webuni.martonBalazs.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import hu.webuni.hr.martonBalazs.dto.EmployeeDto;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIT {
	
	
	private static final String BASE_URI = "/api/emloyees";
	
	@Autowired
	WebTestClient webTestClient;
	
	@Test
	void employeeControllerPut() throws Exception {
		List<EmployeeDto> employeeBefore = getAllEmployees();
		EmployeeDto newEmployee = new EmployeeDto(5L, "Testmetódusvagyok", "Tester", 2, LocalDateTime.parse("2000-01-01T10:00:00"));
		createEmployee(newEmployee);
		List<EmployeeDto> employeeAfter = getAllEmployees();
		
		assertThat(employeeAfter.subList(0, employeeBefore.size())).usingRecursiveFieldByFieldElementComparator().containsExactlyElementsOf(employeeBefore);
		
		assertThat(employeeAfter.get(employeeAfter.size() - 1)).usingRecursiveComparison().isEqualTo(newEmployee);
		
	}

	private void createEmployee(EmployeeDto newEmployee) {
		webTestClient
		.post()
		.uri(BASE_URI)
		.bodyValue(newEmployee)
		.exchange()
		.expectStatus()
		.isOk();
		
	}

	private List<EmployeeDto> getAllEmployees() {
		List<EmployeeDto> responseList = webTestClient
		.put()
		.uri(BASE_URI)
		.exchange()
		.expectStatus()
		.isOk()
		.expectBodyList(EmployeeDto.class)
		.returnResult().getResponseBody();
		
		Collections.sort(responseList, (a1,a2) -> Long.compare(a1.getId(), a2.getId()));
		return responseList;
		
	}
	

}
