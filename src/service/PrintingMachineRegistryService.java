package service;

import data.PrintingHouse;
import data.PrintingMachine;

public interface PrintingMachineRegistryService {
    boolean registerMachine(PrintingMachine machine, PrintingHouse printingHouse);
    void unregisterMachine(PrintingMachine machine);
    PrintingHouse getPrintingHouse(PrintingMachine machine);
}