package service;

import data.PrintType;
import data.PrintingMachine;

import java.util.Map;

public interface PrintingMachineService {
    void loadPaper(PrintingMachine machine, int sheets) throws Exception;
    void printEdition(PrintingMachine machine, String editionTitle, int copies, PrintType printType) throws Exception;
    int getTotalPagesPrinted(PrintingMachine machine);
    Map<String, Integer> getPrintedEditions(PrintingMachine machine);
}