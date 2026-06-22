package com.meh.employeeapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.meh.employeeapi.dto.EmployeeDTO;
import com.meh.employeeapi.entity.Employee;
import com.meh.employeeapi.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO dto) {
        Employee emp = new Employee();
        emp.setName(dto.getName());
        emp.setDepartment(dto.getDepartment());
        emp.setSalary(dto.getSalary());

        Employee saved = repository.save(emp);

        return new EmployeeDTO(saved.getName(), saved.getDepartment(), saved.getSalary());
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return repository.findAll()
                .stream()
                .map(emp -> new EmployeeDTO(emp.getName(), emp.getDepartment(), emp.getSalary()))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        Employee emp = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        return new EmployeeDTO(emp.getName(), emp.getDepartment(), emp.getSalary());
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        Employee emp = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        emp.setName(dto.getName());
        emp.setDepartment(dto.getDepartment());
        emp.setSalary(dto.getSalary());

        Employee updated = repository.save(emp);

        return new EmployeeDTO(updated.getName(), updated.getDepartment(), updated.getSalary());
    }

    @Override
    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }
}