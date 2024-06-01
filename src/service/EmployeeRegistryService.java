package service;

import data.Employee;
import data.PrintingHouse;

public interface EmployeeRegistryService {
    boolean registerEmployee(Employee employee, PrintingHouse printingHouse);
    void unregisterEmployee(Employee employee);
    PrintingHouse getPrintingHouse(Employee employee);
}