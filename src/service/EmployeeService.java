package service;

import data.Employee;

public interface EmployeeService {
    double calculateSalary(Employee employee, double revenue);
}