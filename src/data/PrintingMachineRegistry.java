package data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PrintingMachineRegistry {
    private static Map<UUID, PrintingHouse> machinePrintingHouseMap = new HashMap<>();

    // Registers a machine to a printing house
    public static boolean registerMachine(PrintingMachine machine, PrintingHouse printingHouse) {
        if (machinePrintingHouseMap.containsKey(machine.getUuid())) {
            return false; // Machine is already registered to a printing house
        }
        machinePrintingHouseMap.put(machine.getUuid(), printingHouse);
        return true;
    }

    // Unregisters a machine from its printing house
    public static void unregisterMachine(PrintingMachine machine) {
        machinePrintingHouseMap.remove(machine.getUuid());
    }

    // Gets the printing house a machine is registered to
    public static PrintingHouse getPrintingHouse(PrintingMachine machine) {
        return machinePrintingHouseMap.get(machine.getUuid());
    }
}