package service;

import data.Employee;
import data.Manager;

public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public double calculateSalary(Employee employee, double revenue) {
        if (employee instanceof Manager manager) {
            if (revenue > manager.getRevenueThreshold()) {
                return manager.getBasePay() + (manager.getBasePay() * manager.getBonusPercentage() / 100);
            }
        }
        return employee.getBasePay();
    }
}