import data.*;
import service.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Create paper types
            Paper paperA4 = new Paper(PaperSize.A4, PaperType.PLAIN, 0.1);
            Paper paperA3 = new Paper(PaperSize.A3, PaperType.GLOSSY, 0.2);

            // Create printing houses
            PrintingHouse printingHouse1 = new PrintingHouse("Printing House 1", 100, 10, paperA4, 0.1); // 100 editions for discount, 10% discount, base paper price 0.1
            PrintingHouse printingHouse2 = new PrintingHouse("Printing House 2", 200, 15, paperA3, 0.2); // 200 editions for discount, 15% discount, base paper price 0.2

            // Create services
            EmployeeService employeeService = new EmployeeServiceImpl();
            PrintingMachineService printingMachineService = new PrintingMachineServiceImpl();
            PaperService paperService = new PaperServiceImpl();
            EmployeeRegistryService employeeRegistryService = new EmployeeRegistryServiceImpl();
            PrintingMachineRegistryService printingMachineRegistryService = new PrintingMachineRegistryServiceImpl();
            PrintingHouseService printingHouseService1 = new PrintingHouseServiceImpl(printingHouse1, employeeService, printingMachineService, paperService, employeeRegistryService, printingMachineRegistryService);
            PrintingHouseService printingHouseService2 = new PrintingHouseServiceImpl(printingHouse2, employeeService, printingMachineService, paperService, employeeRegistryService, printingMachineRegistryService);

            // Add printing houses to a collection
            List<PrintingHouseService> printingHouseServices = new ArrayList<>();
            printingHouseServices.add(printingHouseService1);
            printingHouseServices.add(printingHouseService2);

            // Add employees to printingHouse1
            Employee employee1 = new PressOperator("John Doe", 3000);
            Employee employee2 = new Manager("Jane Smith", 5000, 10000, 10);
            try {
                printingHouseService1.addEmployee(employee1);
                printingHouseService1.addEmployee(employee2);
                // Attempt to add the same employee again to test validation
                printingHouseService1.addEmployee(employee1);
            } catch (Exception e) {
                System.err.println("Error adding employee to printingHouse1: " + e.getMessage());
            }

            // Add employees to printingHouse2
            Employee employee3 = new PressOperator("Alice Johnson", 3500);
            Employee employee4 = new Manager("Bob Brown", 5500, 12000, 12);
            try {
                printingHouseService2.addEmployee(employee3);
                printingHouseService2.addEmployee(employee4);
                // Attempt to add the same employee again to test validation
                printingHouseService2.addEmployee(employee3);
            } catch (Exception e) {
                System.err.println("Error adding employee to printingHouse2: " + e.getMessage());
            }

            // Attempt to add an employee from printingHouse1 to printingHouse2
            try {
                printingHouseService2.addEmployee(employee1);
            } catch (Exception e) {
                System.err.println("Error adding employee1 to printingHouse2: " + e.getMessage());
            }

            // Add machines to printingHouse1
            PrintingMachine machine1 = new PrintingMachine(1000, PrintType.COLOR, 60);
            PrintingMachine machine2 = new PrintingMachine(800, PrintType.BLACK_AND_WHITE, 50);
            try {
                printingHouseService1.addMachine(machine1);
                printingHouseService1.addMachine(machine2);
                // Attempt to add the same machine again to test validation
                printingHouseService1.addMachine(machine1);
            } catch (Exception e) {
                System.err.println("Error adding machine to printingHouse1: " + e.getMessage());
            }

            // Add machines to printingHouse2
            PrintingMachine machine3 = new PrintingMachine(1200, PrintType.COLOR, 70);
            PrintingMachine machine4 = new PrintingMachine(900, PrintType.BLACK_AND_WHITE, 55);
            try {
                printingHouseService2.addMachine(machine3);
                printingHouseService2.addMachine(machine4);
                // Attempt to add the same machine again to test validation
                printingHouseService2.addMachine(machine3);
            } catch (Exception e) {
                System.err.println("Error adding machine to printingHouse2: " + e.getMessage());
            }

            // Attempt to add a machine from printingHouse1 to printingHouse2
            try {
                printingHouseService2.addMachine(machine1);
            } catch (Exception e) {
                System.err.println("Error adding machine to printingHouse2: " + e.getMessage());
            }

            // Load paper and print editions for printingHouse1
            try {
                printingMachineService.loadPaper(machine1, 500);
                printingMachineService.printEdition(machine1, "Book1", 100, PrintType.COLOR);
            } catch (Exception e) {
                System.err.println("Error with machine1 in printingHouse1: " + e.getMessage());
            }

            try {
                printingMachineService.loadPaper(machine2, 300);
                printingMachineService.printEdition(machine2, "Newspaper1", 200, PrintType.BLACK_AND_WHITE);
            } catch (Exception e) {
                System.err.println("Error with machine2 in printingHouse1: " + e.getMessage());
            }

            // Load paper and print editions for printingHouse2
            try {
                printingMachineService.loadPaper(machine3, 600);
                printingMachineService.printEdition(machine3, "Magazine1", 150, PrintType.COLOR);
            } catch (Exception e) {
                System.err.println("Error with machine3 in printingHouse2: " + e.getMessage());
            }

            try {
                printingMachineService.loadPaper(machine4, 400);
                printingMachineService.printEdition(machine4, "Flyer1", 250, PrintType.BLACK_AND_WHITE);
            } catch (Exception e) {
                System.err.println("Error with machine4 in printingHouse2: " + e.getMessage());
            }

            // Record revenue and expenses for printingHouse1
            printingHouseService1.recordRevenue("Book1", 100, 200); // 200 is the price per copy
            printingHouseService1.recordRevenue("Newspaper1", 200, 50); // 50 is the price per copy
            printingHouseService1.recordExpense();

            // Record revenue and expenses for printingHouse2
            printingHouseService2.recordRevenue("Magazine1", 150, 300); // 300 is the price per copy
            printingHouseService2.recordRevenue("Flyer1", 250, 20); // 20 is the price per copy
            printingHouseService2.recordExpense();

            // Save data to file for each printing house
            int houseNumber = 1;
            for (PrintingHouseService phs : printingHouseServices) {
                phs.saveDataToFile("printing_house" + houseNumber + "_data.txt");
                houseNumber++;
            }

            // Load data from file for each printing house
            houseNumber = 1;
            for (PrintingHouseService phs : printingHouseServices) {
                phs.loadDataFromFile("src/printing houses data/printing_house" + houseNumber + "_data.txt");
                houseNumber++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}