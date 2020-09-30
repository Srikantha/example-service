package com.ebay.assignment.example;

import com.ebay.assignment.example.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port + "/example-service/employees";
    }
    @Test
    public void contextLoads() {

    }

    @Test
    public void testGetAllEmployees() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl(),
                HttpMethod.GET, entity, String.class);

        assertNotNull(response.getBody());
    }

    @Test
    public void testGetEmployeeById() {
        System.out.println();
        Employee employee = restTemplate.getForObject(getRootUrl() + "/1", Employee.class);
        System.out.println(employee.getFirstName());
        assertNotNull(employee);
    }

    @Test
    public void testCreateEmployee() {
        Employee employee = new Employee();
        employee.setEmailId("admin@gmail.com");
        employee.setFirstName("admin");
        employee.setLastName("admin");

        ResponseEntity<Employee> postResponse = restTemplate.postForEntity(getRootUrl(), employee, Employee.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateEmployee() {
        int id = 1;
        Employee employee = restTemplate.getForObject(getRootUrl() + id, Employee.class);
        employee.setFirstName("admin1");
        employee.setLastName("admin2");

        restTemplate.put(getRootUrl() + id, employee);

        Employee updatedEmployee = restTemplate.getForObject(getRootUrl() + id, Employee.class);
        assertNotNull(updatedEmployee);
    }

    @Test
    public void testDeleteEmployee() {
        int id = 2;
        Employee employee = restTemplate.getForObject(getRootUrl() + id, Employee.class);
        assertNotNull(employee);

        restTemplate.delete(getRootUrl() + id);

        try {
            employee = restTemplate.getForObject(getRootUrl() + id, Employee.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}
