package com.meh.employeeapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meh.employeeapi.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}