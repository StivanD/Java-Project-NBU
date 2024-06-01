package service;

import data.Employee;
import data.PrintingMachine;

import java.io.IOException;

public interface PrintingHouseService {
    void addEmployee(Employee employee) throws Exception;

    void addMachine(PrintingMachine machine) throws Exception;

    void recordRevenue(String editionTitle, int copies, double pricePerCopy);

    void recordExpense();

    void saveDataToFile(String filename) throws IOException;

    void loadDataFromFile(String filename) throws IOException;
}
