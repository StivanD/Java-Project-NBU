package service;

import data.Employee;
import data.PrintingHouse;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EmployeeRegistryServiceImpl implements EmployeeRegistryService {
    private static Map<UUID, PrintingHouse> employeePrintingHouseMap = new HashMap<>();

    @Override
    public boolean registerEmployee(Employee employee, PrintingHouse printingHouse) {
        if (employeePrintingHouseMap.containsKey(employee.getUuid())) {
            return false; // Employee is already registered to a printing house
        }
        employeePrintingHouseMap.put(employee.getUuid(), printingHouse);
        return true;
    }

    @Override
    public void unregisterEmployee(Employee employee) {
        employeePrintingHouseMap.remove(employee.getUuid());
    }

    @Override
    public PrintingHouse getPrintingHouse(Employee employee) {
        return employeePrintingHouseMap.get(employee.getUuid());
    }
}