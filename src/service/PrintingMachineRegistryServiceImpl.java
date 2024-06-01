package service;

import data.PrintingHouse;
import data.PrintingMachine;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PrintingMachineRegistryServiceImpl implements PrintingMachineRegistryService {
    private static Map<UUID, PrintingHouse> machinePrintingHouseMap = new HashMap<>();

    @Override
    public boolean registerMachine(PrintingMachine machine, PrintingHouse printingHouse) {
        if (machinePrintingHouseMap.containsKey(machine.getUuid())) {
            return false; // Machine is already registered to a printing house
        }
        machinePrintingHouseMap.put(machine.getUuid(), printingHouse);
        return true;
    }

    @Override
    public void unregisterMachine(PrintingMachine machine) {
        machinePrintingHouseMap.remove(machine.getUuid());
    }

    @Override
    public PrintingHouse getPrintingHouse(PrintingMachine machine) {
        return machinePrintingHouseMap.get(machine.getUuid());
    }
}