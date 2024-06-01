package service;

import data.PrintType;
import data.PrintingMachine;

import java.util.Map;

public class PrintingMachineServiceImpl implements PrintingMachineService {
    @Override
    public void loadPaper(PrintingMachine machine, int sheets) throws Exception {
        if (machine == null) {
            throw new IllegalArgumentException("Printing machine cannot be null");
        }
        if (sheets < 0) {
            throw new IllegalArgumentException("Number of sheets cannot be negative");
        }
        if (machine.getLoadedSheets() + sheets > machine.getMaxSheets()) {
            throw new Exception("Exceeds maximum sheet capacity");
        }
        machine.setLoadedSheets(machine.getLoadedSheets() + sheets);
    }

    @Override
    public void printEdition(PrintingMachine machine, String editionTitle, int copies, PrintType printType) throws Exception {
        if (machine == null) {
            throw new IllegalArgumentException("Printing machine cannot be null");
        }
        if (editionTitle == null || editionTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Edition title cannot be null or empty");
        }
        if (printType == null) {
            throw new IllegalArgumentException("Print type cannot be null");
        }
        if (copies < 0) {
            throw new IllegalArgumentException("Number of copies cannot be negative");
        }
        if (machine.getPrintType() != printType) {
            throw new Exception("Incompatible print type");
        }
        machine.getPrintedEditions().put(editionTitle, machine.getPrintedEditions().getOrDefault(editionTitle, 0) + copies);
    }

    @Override
    public int getTotalPagesPrinted(PrintingMachine machine) {
        if (machine == null) {
            throw new IllegalArgumentException("Printing machine cannot be null");
        }

        int totalPages = 0;
        for (int copies : machine.getPrintedEditions().values()) {
            totalPages += copies;
        }
        return totalPages;
    }

    @Override
    public Map<String, Integer> getPrintedEditions(PrintingMachine machine) {
        if (machine == null) {
            throw new IllegalArgumentException("Printing machine cannot be null");
        }
        return machine.getPrintedEditions();
    }
}