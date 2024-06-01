package data;

import java.util.*;

public class PrintingHouse {
    private String name;
    private List<Employee> employees;
    private List<PrintingMachine> machines;
    private Set<UUID> employeeIds;
    private Set<UUID> machineIds;
    private double revenue;
    private double expenses;
    private int predefinedNumberOfEditions;
    private double discountPercentage;
    private Paper paper;
    private double basePaperPrice;

    public PrintingHouse(String name, int predefinedNumberOfEditions, double discountPercentage, Paper paper, double basePaperPrice) {
        if (predefinedNumberOfEditions < 0) {
            throw new IllegalArgumentException("Predefined number of editions and discount percentage cannot be negative");
        }
        if (discountPercentage < 0 || discountPercentage > 100) {
            throw new IllegalArgumentException("Discount percentage must be between 0 and 100");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
        this.employees = new ArrayList<>();
        this.machines = new ArrayList<>();
        this.employeeIds = new HashSet<>();
        this.machineIds = new HashSet<>();
        this.revenue = 0;
        this.expenses = 0;
        this.predefinedNumberOfEditions = predefinedNumberOfEditions;
        this.discountPercentage = discountPercentage;
        this.paper = paper;
        this.basePaperPrice = basePaperPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Employee> getEmployees() {
        return employees;
    }

    public List<PrintingMachine> getMachines() {
        return machines;
    }

    public Set<UUID> getEmployeeIds() {
        return employeeIds;
    }

    public Set<UUID> getMachineIds() {
        return machineIds;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    public int getPredefinedNumberOfEditions() {
        return predefinedNumberOfEditions;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public Paper getPaper() {
        return paper;
    }

    public double getBasePaperPrice() {
        return basePaperPrice;
    }
}