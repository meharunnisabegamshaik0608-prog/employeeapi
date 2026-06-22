package com.meh.employeeapi.service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.meh.employeeapi.dto.EmployeeDTO;
import com.meh.employeeapi.entity.Employee;
import com.meh.employeeapi.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeServiceImpl service;

    private Employee employee;

    @BeforeEach
    public void setUp() {

        employee = new Employee();
        employee.setId(1L);
        employee.setName("John");
        employee.setDepartment("IT");
        employee.setSalary(50000.0);
    }

    @Test
    public void testGetEmployeeById() {

        when(repository.findById(1L))
                .thenReturn(Optional.of(employee));

        EmployeeDTO result = service.getEmployeeById(1L);

        assertEquals("John", result.getName());
        assertEquals("IT", result.getDepartment());
        assertEquals(50000.0, result.getSalary());
    }
}