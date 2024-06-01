package data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EmployeeRegistry {
    private static Map<UUID, PrintingHouse> employeePrintingHouseMap = new HashMap<>();

    // Registers an employee to a printing house
    public static boolean registerEmployee(Employee employee, PrintingHouse printingHouse) {
        if (employeePrintingHouseMap.containsKey(employee.getUuid())) {
            return false; // Employee is already registered to a printing house
        }
        employeePrintingHouseMap.put(employee.getUuid(), printingHouse);
        return true;
    }

    // Unregisters an employee from their printing house
    public static void unregisterEmployee(Employee employee) {
        employeePrintingHouseMap.remove(employee.getUuid());
    }

    // Gets the printing house an employee is registered to
    public static PrintingHouse getPrintingHouse(Employee employee) {
        return employeePrintingHouseMap.get(employee.getUuid());
    }
}