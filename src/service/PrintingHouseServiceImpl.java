package service;

import data.Employee;
import data.PrintingHouse;
import data.PrintingMachine;

import java.io.*;
import java.util.Map;

public class PrintingHouseServiceImpl implements PrintingHouseService {
    private PrintingHouse printingHouse;
    private EmployeeService employeeService;
    private PrintingMachineService printingMachineService;
    private PaperService paperService;
    private EmployeeRegistryService employeeRegistryService;
    private PrintingMachineRegistryService printingMachineRegistryService;

    public PrintingHouseServiceImpl(PrintingHouse printingHouse, EmployeeService employeeService, PrintingMachineService printingMachineService, PaperService paperService, EmployeeRegistryService employeeRegistryService, PrintingMachineRegistryService printingMachineRegistryService) {
        this.printingHouse = printingHouse;
        this.employeeService = employeeService;
        this.printingMachineService = printingMachineService;
        this.paperService = paperService;
        this.employeeRegistryService = employeeRegistryService;
        this.printingMachineRegistryService = printingMachineRegistryService;
    }

    @Override
    public void addEmployee(Employee employee) throws Exception {
        if (printingHouse.getEmployees().contains(employee)) {
            throw new Exception("Employee with ID " + employee.getUuid() + " is already present in the printing house.");
        }
        if (employeeRegistryService.getPrintingHouse(employee) != null) {
            throw new Exception("Employee with ID " + employee.getUuid() + " is already registered to another printing house.");
        }
        printingHouse.getEmployees().add(employee);
        employeeRegistryService.registerEmployee(employee, printingHouse);
    }

    @Override
    public void addMachine(PrintingMachine machine) throws Exception {
        if (printingHouse.getMachines().contains(machine)) {
            throw new Exception("Machine with ID " + machine.getUuid() + " is already present in the printing house.");
        }
        if (printingMachineRegistryService.getPrintingHouse(machine) != null) {
            throw new Exception("Machine with ID " + machine.getUuid() + " is already registered to another printing house.");
        }

        printingHouse.getMachines().add(machine);
        printingMachineRegistryService.registerMachine(machine, printingHouse);
    }

    @Override
    public void recordRevenue(String editionTitle, int copies, double pricePerCopy) {
        if (copies < 0 || pricePerCopy < 0) {
            throw new IllegalArgumentException("Number of copies and price per copy cannot be negative");
        }
        double totalRevenue = copies * pricePerCopy;
        if (copies > printingHouse.getPredefinedNumberOfEditions()) {
            totalRevenue -= totalRevenue * (printingHouse.getDiscountPercentage() / 100);
        }
        printingHouse.setRevenue(printingHouse.getRevenue() + totalRevenue);
    }

    @Override
    public void recordExpense() {
        double totalExpenses = 0;

        for (Employee employee : printingHouse.getEmployees()) {
            totalExpenses += employeeService.calculateSalary(employee, printingHouse.getRevenue());
        }

        for (PrintingMachine machine : printingHouse.getMachines()) {
            for (Map.Entry<String, Integer> entry : printingMachineService.getPrintedEditions(machine).entrySet()) {
                String editionTitle = entry.getKey();
                int copies = entry.getValue();
                int sheetsPerCopy = 1; // This should be defined based on the edition
                double paperCostPerSheet = paperService.calculatePrice(printingHouse.getPaper(), printingHouse); // This should be defined based on the paper size
                totalExpenses += copies * sheetsPerCopy * paperCostPerSheet;
            }
        }

        printingHouse.setExpenses(printingHouse.getExpenses() + totalExpenses);
    }

    @Override
    public void saveDataToFile(String filename) throws IOException {
        String directoryName = "src/printing houses data";
        File directory = new File(directoryName);
        if (!directory.exists()) {
            directory.mkdir();
        }

        File file = new File(directory, filename);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("Printing House: " + printingHouse.getName() + "\n");
            writer.write("- Revenue: " + printingHouse.getRevenue() + "\n");
            writer.write("- Expenses: " + printingHouse.getExpenses() + "\n");
            writer.write("- Employees:\n");
            for (Employee employee : printingHouse.getEmployees()) {
                writer.write("  - " + employee.getName() + ":\n");
                writer.write("     " + "UUID: " + employee.getUuid() + "\n");
                writer.write("     " + "Salary: " + employeeService.calculateSalary(employee, printingHouse.getRevenue()) + "\n");
                writer.write("\n");
            }
            writer.write("- Machines:\n");
            int count = 1;
            for (PrintingMachine machine : printingHouse.getMachines()) {
                writer.write("  - Machine " + count + ": " + "\n");
                writer.write("     " + "UUID: " + machine.getUuid() + ":\n");
                for (Map.Entry<String, Integer> entry : printingMachineService.getPrintedEditions(machine).entrySet()) {
                    writer.write("     Edition: " + entry.getKey() + "\n");
                }
                writer.write("     Printed pages: " + printingMachineService.getTotalPagesPrinted(machine) + "\n");
                writer.write("\n");
                count += 1;
            }
        } catch (IOException e) {
            System.err.println("Error saving data to file: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void loadDataFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error loading data from file: " + e.getMessage());
            throw e;
        }
    }
}